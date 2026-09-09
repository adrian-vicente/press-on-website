package backend.backend_v1.mapper;

import backend.backend_v1.config.ValidadorConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import backend.backend_v1.dto.Usuario.UsuarioCreateDTO;
import backend.backend_v1.dto.Usuario.UsuarioDTO;
import backend.backend_v1.model.Usuario;

@Configuration 
public class UsuarioMapper {

    // Inyección de dependencias

    private final PasswordEncoder passwordEncoder;
    public UsuarioMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // Método de conversión a entidad

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
            if(ValidadorConfig.validarIdentificador(dto.getId())) {
                usuario.setId(dto.getId());

            } // if
            
            if(ValidadorConfig.validarFecha(dto.getFechaCreacion())) {
                usuario.setFechaCreacion(dto.getFechaCreacion());
            
            } else usuario.setFechaCreacion(null);

            if(ValidadorConfig.validarFecha(dto.getFechaActualizacion())) {
                usuario.setFechaActualizacion(dto.getFechaActualizacion());

            } else usuario.setFechaActualizacion(null);

            usuario.setNombre(dto.getNombre());
            usuario.setApellidos(dto.getApellidos());
            usuario.setEmail(dto.getEmail());
            usuario.setFotoPerfil_url(dto.getFotoPerfil_url());
        
        return usuario;
    }

    // Método de conversión a dto

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
            if(ValidadorConfig.validarIdentificador(usuario.getId())) {
                usuarioDTO.setId(usuario.getId());
             
            } // if

            if(ValidadorConfig.validarFecha(usuario.getFechaCreacion())) {
                usuarioDTO.setFechaCreacion(usuario.getFechaCreacion());

            } else usuarioDTO.setFechaCreacion(null);

            if(ValidadorConfig.validarFecha(usuario.getFechaActualizacion())) {
                usuarioDTO.setFechaActualizacion(usuario.getFechaActualizacion());

            } else usuarioDTO.setFechaActualizacion(null);

            usuarioDTO.setNombre(usuario.getNombre());
            usuarioDTO.setApellidos(usuario.getApellidos());
            usuarioDTO.setFotoPerfil_url(usuario.getFotoPerfil_url());
            usuarioDTO.setEmail(usuario.getEmail());

        return usuarioDTO;

    }

    // Método de conversión a entidad de dto de creación

    public Usuario toEntityFromCreateDTO(UsuarioCreateDTO usuarioCreate) {
        Usuario usuario = new Usuario();
            usuario.setNombre(usuarioCreate.getNombre());
            usuario.setApellidos(usuarioCreate.getApellidos());
            usuario.setFotoPerfil_url(usuarioCreate.getFotoPerfil_url());
            usuario.setEmail(usuarioCreate.getEmail());
            usuario.setPassword( passwordEncoder.encode(usuarioCreate.getPassword()) );
            usuario.setFechaCreacion(LocalDateTime.now());

        return usuario;

    }

} // class