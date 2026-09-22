package backend.backend_v1.dto.Usuario;

import java.time.LocalDateTime;

import backend.backend_v1.model.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
public class UsuarioUpdateDTO {

    // Declaración de atributos 

    @NotNull(message = "El id es necesario para modificar los datos del usuario.")  
    private Long id;
    
    @NotBlank(message = "El nombre del usuario no puede estar vacío.")
    @NotEmpty(message = "El nombre del usuario no puede estar vacío.")
    private String nombre;

    @NotBlank(message = "Los apellidos del usuario no pueden estar vacíos.")
    @NotEmpty(message = "Los apellidos del usuario no pueden estar vacíos.")
    private String apellidos;

    private String fotoPerfil_url;
    
    @NotBlank(message = "El email del usuario no puede estar vacío.")
    @NotEmpty(message = "El email del usuario no puede estar vacío.")
    private String email;
    
    private String password;
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private Rol rol;

} // class