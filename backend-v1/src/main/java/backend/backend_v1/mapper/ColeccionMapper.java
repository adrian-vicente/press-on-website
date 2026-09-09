package backend.backend_v1.mapper;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;

import backend.backend_v1.config.ValidadorConfig;
import backend.backend_v1.dto.Coleccion.ColeccionCreateDTO;
import backend.backend_v1.dto.Coleccion.ColeccionDTO;
import backend.backend_v1.model.Coleccion;

@Configuration 
public class ColeccionMapper {

    // Método de conversión de entidad a dto

    private final ValidadorConfig validadorConfig;

    ColeccionMapper(ValidadorConfig validadorConfig) {
        this.validadorConfig = validadorConfig;
    }

    public Coleccion toEntity(ColeccionDTO coleccionDTO) {
        Coleccion coleccion = new Coleccion();
        
            if(ValidadorConfig.validarIdentificador(coleccionDTO.getId())) {
                coleccion.setId(coleccionDTO.getId());

            } // if

            if(ValidadorConfig.validarFecha(coleccionDTO.getFechaCreacion())) {
                coleccion.setFechaCreacion(coleccionDTO.getFechaCreacion());

            } else coleccion.setFechaCreacion(null);

            coleccion.setNombre(coleccionDTO.getNombre());
            coleccion.setDescripcion(coleccionDTO.getDescripcion());
            coleccion.setFoto_url(coleccionDTO.getFoto_url());

        return coleccion;

    } // toEntity

    // Método de conversión de dto a entidad

    public ColeccionDTO toDTO(Coleccion coleccion) {
        ColeccionDTO coleccionDTO = new ColeccionDTO();
            
            if(ValidadorConfig.validarIdentificador(coleccion.getId())) {
                coleccionDTO.setId(coleccion.getId());
                 
            } // if

            if(ValidadorConfig.validarFecha(coleccion.getFechaCreacion())) {
                coleccionDTO.setFechaCreacion(coleccion.getFechaCreacion());

            } else coleccionDTO.setFechaCreacion(null);

            if(ValidadorConfig.validarFecha(coleccion.getFechaActualizacion())) {
                coleccionDTO.setFechaActualizacion(coleccion.getFechaActualizacion());
            
            } else coleccionDTO.setFechaActualizacion(null);

            coleccionDTO.setNombre(coleccion.getNombre());
            coleccionDTO.setDescripcion(coleccion.getDescripcion());
            coleccionDTO.setFoto_url(coleccion.getFoto_url());
            
        return coleccionDTO;

    } // toDTO

    // Método de conversión dto de creación a entidad

    public Coleccion toEntityFromCreateDTO(ColeccionCreateDTO coleccionCreate) {
        Coleccion coleccion = new Coleccion();
            coleccion.setNombre(coleccionCreate.getNombre());
            coleccion.setDescripcion(coleccionCreate.getDescripcion());
            coleccion.setFoto_url(coleccionCreate.getFoto_url());
            coleccion.setFechaCreacion(LocalDateTime.now());

        return coleccion;

    } // toEntityFromCreateDTO

    // Método de conversión dto de modificación a entidad
    
} // class