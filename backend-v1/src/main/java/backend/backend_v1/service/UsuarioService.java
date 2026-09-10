package backend.backend_v1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.backend_v1.dto.Usuario.UsuarioCreateDTO;
import backend.backend_v1.dto.Usuario.UsuarioDTO;
import backend.backend_v1.exception.Usuario.UsuarioAlreadyExistsException;
import backend.backend_v1.mapper.UsuarioMapper;
import backend.backend_v1.model.Usuario;
import backend.backend_v1.repository.UsuarioRepository;

@Service 
public class UsuarioService {

    // Inyección de dependencias

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;

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

} // class