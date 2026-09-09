package backend.backend_v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_v1.dto.Login.AuthResponseDTO;
import backend.backend_v1.dto.Login.LoginRequestDTO;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody  @Valid LoginRequestDTO loginRequestDTO) {
        String token = authService.login(loginRequestDTO.email(), loginRequestDTO.password());
        return ResponseEntity.ok( new AuthResponseDTO(token) );

    }

} // class