package backend.backend_v1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_v1.dto.Usuario.UsuarioCreateDTO;
import backend.backend_v1.dto.Usuario.UsuarioDTO;
import backend.backend_v1.exception.Usuario.UsuarioAlreadyExistsException;
import backend.backend_v1.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("/api/usuarios")
public class UsuarioRestController {

    // Inyección de dependencias

    private final UsuarioService usuarioService;
    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    // Método para la creación de un nuevo usuario 

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody @Valid UsuarioCreateDTO usuarioCreate) throws UsuarioAlreadyExistsException {
        UsuarioDTO usuarioNuevo = usuarioService.crearUsuario(usuarioCreate);
        return ResponseEntity.ok(usuarioNuevo);

    }

} // class