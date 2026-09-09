package backend.backend_v1.dto.Producto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
public class ProductoUpdateDTO {

    // Declaración de atributos 

    private Long id;
    private String nombre;
    private String descripcion;
    private String foto_url;
    private Double precio;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

} // class