package backend.backend_v1.config;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

@Configuration 
public class ValidadorConfig {

    // Método para validar el identificador

    public static boolean validarIdentificador(Long id) {
        return id != null && id > 0;
    }

    // Método para validar una fecha

    public static boolean validarFecha(LocalDateTime fecha) {
        return fecha != null;
    }

} // class