package backend.backend_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.backend_v1.model.Usuario;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
