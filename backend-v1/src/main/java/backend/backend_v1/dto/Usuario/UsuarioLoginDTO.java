package backend.backend_v1.dto.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
public class UsuarioLoginDTO {

    private String email;
    private String password;

} // class