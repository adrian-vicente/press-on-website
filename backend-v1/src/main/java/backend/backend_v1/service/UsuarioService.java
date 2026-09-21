package backend.backend_v1.service;

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
    public UsuarioDTO modificarUsuarioExistente(HttpSession session, UsuarioUpdateDTO usuarioModificado) throws UsuarioAlreadyExistsException, UsernameNotFoundException, RuntimeException {

        // Comprobar si existe un usuario con el email del modificado 

        if(usuarioRepository.existsByEmail(usuarioModificado.getEmail())) {
            throw new UsuarioAlreadyExistsException("Ya existe un usuario con el mail: " + usuarioModificado.getEmail());
        
        }

        // Obtener la entidad con los datos modificados del usuario

        UsuarioDTO usuarioActual = authService.obtenerUsuarioAutenticado(session);
        Usuario usuario = usuarioMapper.toEntityFromUpdateDTO(usuarioModificado, usuarioActual);

        // Guardar los datos del usuario actual 

        usuarioRepository.saveAndFlush(usuario);

        // Devolver el usuario en formato DTO para el frontend 

        return usuarioMapper
            .toDTO(usuario);

    }

} // class