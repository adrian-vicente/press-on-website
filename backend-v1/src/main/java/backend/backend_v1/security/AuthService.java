package backend.backend_v1.security;

import backend.backend_v1.mapper.UsuarioMapper;
import backend.backend_v1.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import backend.backend_v1.dto.Usuario.UsuarioDTO;
import backend.backend_v1.exception.Usuario.UsernameNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {

    // Inyección de dependencias

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final AuthenticationManager authenticationManager;

    // Método para iniciar sesión en la aplicación

    public void iniciarSesion(String email, String password, HttpSession session) throws BadCredentialsException {
        Authentication authentication = 
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)

            );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
        session
            .setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
            );

    }

    // Método que permite cerrar sesión en la aplicación 

    public void cerrarSesion(HttpSession session) {
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();

        } // if

    }

    // Método para obtener usuario autenticado a partir de la cookie de sesión

    public UsuarioDTO obtenerUsuarioAutenticado(HttpSession session) throws UsernameNotFoundException, RuntimeException {
        SecurityContext context = (SecurityContext) session.getAttribute(
            HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY
        );

        if(context == null || context.getAuthentication() == null) {
            throw new RuntimeException("No existe un usuario autenticado.");

        } // if 

        Authentication authentication = context.getAuthentication();
        String email = authentication.getName();

        return usuarioMapper.toDTO(
            usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No se ha encontrado a ningún usuario con email: " + email))   
        );

    }

} // class