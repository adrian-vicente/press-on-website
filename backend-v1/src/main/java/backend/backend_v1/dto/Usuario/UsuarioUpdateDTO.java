package backend.backend_v1.dto.Usuario;

import java.time.LocalDateTime;
import backend.backend_v1.model.Rol;
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

    private Long id;
    private String nombre;
    private String apellidos;
    private String fotoPerfil_url;
    private String email;
    private String password;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private Rol rol;

} // class