package backend.backend_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_v1.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{

    // Método para saber si existe un producto por nombre 

    public boolean existsByNombre(String nombre);

    // Encontrar producto a partir del nombre

    public Optional<Producto> findByNombre(String nombre);

}