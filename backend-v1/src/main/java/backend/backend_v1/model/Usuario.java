package backend.backend_v1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "usuarios")
@NoArgsConstructor 
@AllArgsConstructor 
@Getter 
@Setter 
public class Usuario {

    // Declaración de atributos 

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String apellidos;
    private String fotoPerfil_url;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

} // class