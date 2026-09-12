package backend.backend_v1.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {

    // Inyección de dependencias

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

} // class