package backend.backend_v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.backend_v1.dto.Login.LoginRequestDTO;
import backend.backend_v1.dto.Login.RefreshTokenRequestDTO;
import backend.backend_v1.security.AuthService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/auth")
public class AuthController {

} // class