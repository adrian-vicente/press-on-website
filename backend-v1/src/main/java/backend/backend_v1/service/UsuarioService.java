package backend.backend_v1.service;

import org.springframework.stereotype.Service;

import backend.backend_v1.repository.UsuarioRepository;

@Service 
public class UsuarioService {

    // Inyección de dependencias

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    // Método para iniciar sesión 

} // class