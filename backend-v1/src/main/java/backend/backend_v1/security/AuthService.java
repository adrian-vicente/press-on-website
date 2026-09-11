package backend.backend_v1.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import backend.backend_v1.dto.Login.AuthResponseDTO;

@Service 
public class AuthService {

    // Inyección de dependencias

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthService(CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = customUserDetailsService;

    }

    // Método que permite iniciar sesión 

    public AuthResponseDTO login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Generar token y acess token 

        String accessToken = jwtService.generarAccessToken(userDetails);
        String refreshToken = jwtService.generarRefreshToken(userDetails);

        return new AuthResponseDTO(accessToken, refreshToken);

    } // login

    // Método que hace la renovación del token

    public AuthResponseDTO refreshToken(String refreshToken) {

        // Comprobar que es realmente un refresh token 

        if(!jwtService.esRefreshToken(refreshToken)) {
            throw new RuntimeException("El token proporcionado no es un refresh token.");

        }

        // Obtener el username que será el email del usuario 

        String username_email = jwtService.extraerUsername(refreshToken);

        // Buscar los datos del Usuario

        UserDetails userDetails = userDetailsService.loadUserByUsername(username_email);

        // Comprobar que el token pertenece al usuario y sigue siendo válido 

        if(!jwtService.tokenEsValido(refreshToken, userDetails)) {
            throw new RuntimeException("El refresh token no es válido.");

        }

        // Generar un nuevo access token 

        String accessToken = jwtService.generarAccessToken(userDetails);

        // Devolver el nuevo access token y el refresh token existente 

        return new AuthResponseDTO(
            accessToken,
            refreshToken
        );

    }

} // class