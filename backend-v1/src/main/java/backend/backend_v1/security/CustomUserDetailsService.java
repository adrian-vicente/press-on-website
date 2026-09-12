package backend.backend_v1.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import backend.backend_v1.exception.Usuario.UsernameNotFoundException;
import backend.backend_v1.model.Usuario;
import backend.backend_v1.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CustomUserDetailsService implements UserDetailsService{

    // Inyección de dependencias

    private final UsuarioRepository usuarioRepository;

    @Override 
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado a ningún usuario con email: " + email));

        return User.builder()
            .username(usuario.getEmail())
            .password(usuario.getPassword())
            .roles(usuario.getRol().name())
            .build();

    }

} // class