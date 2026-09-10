package backend.backend_v1.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import backend.backend_v1.exception.Usuario.UsernameNotFoundException;
import backend.backend_v1.model.Usuario;
import backend.backend_v1.repository.UsuarioRepository;

@Service 
public class CustomUserDetailsService implements UserDetailsService{

    // Inyección de dependencias

    private final UsuarioRepository usuarioRepository;
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Implementación método de la interfaz

    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado ningún usuario con el email: " + username));

        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getPassword())
            .roles("USUARIO")
            .build();

    }

} // class