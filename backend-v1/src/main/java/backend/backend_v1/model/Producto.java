package backend.backend_v1.model;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "productos")
@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
public class Producto {

    // Declaración de atributos

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;
    
    private String descripcion;
    private String foto_url;
    private Double precio;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    
    // Declaración de atributos para las relaciones

    @ManyToMany (mappedBy = "productos")
    private List<Coleccion> colecciones;

} // class