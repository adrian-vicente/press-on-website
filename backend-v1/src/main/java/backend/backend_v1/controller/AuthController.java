package backend.backend_v1.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_v1.dto.Login.LoginRequestDTO;
import backend.backend_v1.security.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/auth")
@RequiredArgsConstructor 
public class AuthController {

    // Inyección de dependencias

    private final AuthService authService;

    // Método que permite iniciar sesión en la aplicación

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO loginRequest, HttpSession session) throws BadCredentialsException {
        authService.iniciarSesion(loginRequest.email(), loginRequest.password(), session);
        return ResponseEntity
            .ok("Se ha iniciado sesión correctamente.");

    }

    // Método que permite cerrar sesión en la aplicación 

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        authService.cerrarSesion(session);
        return ResponseEntity
            .ok("La sesión se ha cerrado correctamente.");
    }

} // class