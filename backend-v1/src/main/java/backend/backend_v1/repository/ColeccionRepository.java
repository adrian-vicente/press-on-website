package backend.backend_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_v1.model.Coleccion;

@Repository 
public interface ColeccionRepository extends JpaRepository<Coleccion, Long>{

    // Comprobar si existe alguna colección por nombre

    public boolean existsByNombre(String nombre);

    // Encontrar colección por nombre

    public Optional<Coleccion> findByNombre(String nombre);

}
