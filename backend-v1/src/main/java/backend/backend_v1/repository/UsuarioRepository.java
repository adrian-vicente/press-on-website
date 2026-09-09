package backend.backend_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_v1.model.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    // Método para encontrar usuario a partir del email

    public Optional<Usuario> findByEmail(String email);

    // Comprobar si existe usuario con mail determinado 

    public boolean existsByEmail(String email);

} // interface