package backend.backend_v1.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.backend_v1.dto.Usuario.UsuarioCreateDTO;
import backend.backend_v1.dto.Usuario.UsuarioDTO;
import backend.backend_v1.dto.Usuario.UsuarioUpdateDTO;
import backend.backend_v1.exception.Usuario.UsernameNotFoundException;
import backend.backend_v1.exception.Usuario.UsuarioAlreadyExistsException;
import backend.backend_v1.mapper.UsuarioMapper;
import backend.backend_v1.model.Usuario;
import backend.backend_v1.repository.UsuarioRepository;
import backend.backend_v1.security.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UsuarioService {

    // Inyección de dependencias

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final AuthService authService;

    // Método para registrar un nuevo usuario

    @Transactional 
    public UsuarioDTO crearUsuario(UsuarioCreateDTO usuarioCreate) throws UsuarioAlreadyExistsException {
        if(usuarioRepository.existsByEmail(usuarioCreate.getEmail())) {
            throw new UsuarioAlreadyExistsException("El usuario con email: " + usuarioCreate.getEmail() + " ya existe.");

        } // if

        Usuario usuarioNuevo = usuarioMapper.toEntityFromCreateDTO(usuarioCreate);
        return usuarioMapper.toDTO( usuarioRepository.saveAndFlush(usuarioNuevo) );

    } // crearUsuario

    // Método para obtener usuario a partir del email

    @Transactional(readOnly = true)
    public UsuarioDTO obtenerUsuarioPorEmail(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado a ningún usuario con el email: " + email));

        return usuarioMapper.toDTO(usuario);

    }

    // Método para modificar un usuario existente

    @Transactional 
    public UsuarioDTO modificarUsuarioExistente(HttpSession session, UsuarioUpdateDTO usuarioModificado) throws UsuarioAlreadyExistsException, UsernameNotFoundException, IllegalAccessException, NoSuchFieldException {

        // Obtener el usuario actual 

        UsuarioDTO usuarioActual_dto = authService.obtenerUsuarioAutenticado(session);

        // Obtener la entidad del usuario actual (Con el campo password)
        
        Usuario usuario = usuarioRepository.findByEmail(usuarioActual_dto.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado a ningún usuario con email: " + usuarioActual_dto.getEmail()));
        
        // Comprobar la correspondencia entre datos del usuario modificado y guardar el usuario

        usuario.setFechaActualizacion(LocalDateTime.now());
        usuario.setFotoPerfil_url("no_implementado");

        if(usuarioModificado.getNombre() != null && !usuarioModificado.getNombre().trim().toLowerCase().equalsIgnoreCase(usuario.getNombre().trim().toLowerCase())) {
            usuario.setNombre(usuarioModificado.getNombre());

        } // if

        if(usuarioModificado.getApellidos() != null && !usuarioModificado.getApellidos().trim().toLowerCase().equalsIgnoreCase(usuario.getApellidos().trim().toLowerCase())) {
            usuario.setPassword(usuarioModificado.getApellidos());

        } // if

        if(usuarioModificado.getEmail() != null && !usuario.getEmail().equalsIgnoreCase(usuarioModificado.getEmail()) && usuarioRepository.existsByEmail(usuarioModificado.getEmail())) {
            throw new UsuarioAlreadyExistsException("Ya existe un usuario con el email: " + usuarioModificado.getEmail());

        } else if (!usuarioModificado.getEmail().equalsIgnoreCase(usuario.getEmail())) {
            usuario.setEmail(usuarioModificado.getEmail());

        } // if - else if

        if(usuarioModificado.getPassword() != null && !usuarioModificado.getPassword().isBlank()) {
            
            // Se ha introducido una nueva password por lo cuál se borra la actual y se cifra la nueva 

            usuario.setPassword(passwordEncoder.encode(usuarioModificado.getPassword()));

        }

        // Devolver el objeto con los datos transpilados 

        return usuarioMapper.toDTO(
            usuarioRepository.saveAndFlush(usuario)

        );

     }

} // class