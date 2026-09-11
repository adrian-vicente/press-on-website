package backend.backend_v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_v1.dto.Login.AuthResponseDTO;
import backend.backend_v1.dto.Login.LoginRequestDTO;
import backend.backend_v1.dto.Login.RefreshTokenRequestDTO;
import backend.backend_v1.security.AuthService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/auth")
public class AuthController {

    // Inyección de dependencias 

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;

    }

    // Método para iniciar sesión en la aplicación

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody  @Valid LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(
            authService.login(
                loginRequestDTO.email(), 
                loginRequestDTO.password()
            )
        );

    }

    // Método que permite generar un refresh token 

    @PreAuthorize("hasRole('USUARIO')")
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(@RequestBody @Valid RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return ResponseEntity.ok(
            authService.refreshToken(
                refreshTokenRequestDTO.refreshToken()
            )
        );

    }

} // class