package backend.backend_v1.dto.Usuario;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "El usuario debe tener un nombre para crearlo. ")
    private String nombre;

    private String apellidos;
    private String fotoPerfil_url;
   
    @NotBlank(message = "El usuario debe de tener un mail.")
    private String email;

    @NotBlank(message = "El usuario debe tener una password.")
    private String password;
    private LocalDateTime fechaCreacion;

} // class