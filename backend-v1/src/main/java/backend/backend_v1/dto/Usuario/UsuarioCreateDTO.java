package backend.backend_v1.dto.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor 
@NoArgsConstructor 
@Getter 
@Setter 
public class UsuarioCreateDTO {

    // Declaración de atributos 

    private String nombre;
    private String apellidos;
    private String fotoPerfil_url;
    private String email;
    private String password;
    private LocalDateTime fechaCreacion;

} // class