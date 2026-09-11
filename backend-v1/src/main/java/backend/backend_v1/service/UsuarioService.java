package backend.backend_v1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.backend_v1.dto.Login.AuthResponseDTO;
import backend.backend_v1.dto.Usuario.UsuarioCreateDTO;
import backend.backend_v1.dto.Usuario.UsuarioDTO;
import backend.backend_v1.exception.General.GeneralErrorProductionException;
import backend.backend_v1.exception.Usuario.UsernameNotFoundException;
import backend.backend_v1.exception.Usuario.UsuarioAlreadyExistsException;
import backend.backend_v1.mapper.UsuarioMapper;
import backend.backend_v1.model.Usuario;
import backend.backend_v1.repository.UsuarioRepository;
import backend.backend_v1.security.JwtService;

@Service 
public class UsuarioService {

    // Inyección de dependencias

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final JwtService jwtService;
    
    public UsuarioService(JwtService jwtService, UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.jwtService = jwtService;

    }

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

    // Método para obtener un usuario autenticado 

} // class