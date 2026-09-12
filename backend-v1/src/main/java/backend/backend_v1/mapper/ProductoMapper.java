package backend.backend_v1.mapper;

import java.time.LocalDateTime;
import org.springframework.context.annotation.Configuration;
import backend.backend_v1.config.ValidadorConfig;
import backend.backend_v1.dto.Producto.ProductoCreateDTO;
import backend.backend_v1.dto.Producto.ProductoDTO;
import backend.backend_v1.dto.Producto.ProductoUpdateDTO;
import backend.backend_v1.model.Producto;

@Configuration 
public class ProductoMapper {

    // Método de conversión a entidad

    public Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();

            if(ValidadorConfig.validarIdentificador(dto.getId())) {
                producto.setId(dto.getId());

            } else return null;

            if(ValidadorConfig.validarFecha(dto.getFechaCreacion())) {
                producto.setFechaCreacion(dto.getFechaCreacion());

            } else producto.setFechaCreacion(null);

            producto.setNombre(dto.getNombre());
            producto.setDescripcion(dto.getDescripcion());
            producto.setFoto_url(dto.getFoto_url());
            producto.setPrecio(dto.getPrecio());

        return producto;

    } // toEntity

    // Método de conversión a dto 

    public ProductoDTO toDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
            
            if(ValidadorConfig.validarFecha(producto.getFechaCreacion())) {
                productoDTO.setFechaCreacion(producto.getFechaCreacion());
            
            } else productoDTO.setFechaCreacion(null);

            if(ValidadorConfig.validarFecha(producto.getFechaActualizacion())) {
                productoDTO.setFechaActualizacion(producto.getFechaActualizacion());

            } else productoDTO.setFechaActualizacion(null);

            productoDTO.setId(producto.getId());
            productoDTO.setNombre(producto.getNombre());
            productoDTO.setDescripcion(producto.getDescripcion());
            productoDTO.setPrecio(producto.getPrecio());
            producto.setFechaCreacion(producto.getFechaCreacion());

        return productoDTO;

    }

    // Método de conversión de create dto a entidad 

    public Producto toEntityFromCreateDTO(ProductoCreateDTO productoCreate) {
        Producto producto = new Producto();
            producto.setNombre(productoCreate.getNombre());
            producto.setDescripcion(productoCreate.getDescripcion());
            producto.setFoto_url(productoCreate.getFoto_url());
            producto.setPrecio(productoCreate.getPrecio());
            producto.setFechaCreacion(LocalDateTime.now());

        return producto;

    }

    // Método de conversión de update dto a entidad

    public Producto toEntityFromUpdateDTO(ProductoDTO productoActual, ProductoUpdateDTO productoUpdate) {
        Producto producto = new Producto();
            producto.setId(productoActual.getId());
            producto.setFechaCreacion(productoActual.getFechaCreacion());
            producto.setFechaActualizacion(LocalDateTime.now());

        // Comprobaciones sobre los datos en el dto

        if(productoActual.getNombre() != null && (productoUpdate.getNombre() != null && !productoUpdate.getNombre().toLowerCase().trim().equals(productoActual.getNombre().toLowerCase().trim())) ) {
            producto.setNombre(productoUpdate.getNombre());

        } // if

        if(productoActual.getDescripcion() != null && (productoUpdate.getDescripcion() != null && !productoUpdate.getDescripcion().toLowerCase().trim().equals(productoActual.getDescripcion().toLowerCase().trim()))) {
            producto.setDescripcion(productoUpdate.getDescripcion());

        } // if

        if(productoActual.getFoto_url() != null && (productoUpdate.getFoto_url() != null && !productoUpdate.getFoto_url().toLowerCase().trim().equals(productoActual.getFoto_url().toLowerCase().trim()))) {
            producto.setFoto_url(productoUpdate.getFoto_url());

        } // if

        if(productoActual.getPrecio() != null && (productoUpdate.getPrecio() != null && productoUpdate.getPrecio() != productoActual.getPrecio() )) {
            producto.setPrecio(productoUpdate.getPrecio());

        } // if

        // Devolver el producto con la información actualizada

        return producto;

    } // toEntityFromUpdateDTO

} // class