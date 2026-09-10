package backend.backend_v1.service;

import java.util.List;
import backend.backend_v1.model.Producto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import backend.backend_v1.dto.Coleccion.ColeccionCreateDTO;
import backend.backend_v1.dto.Coleccion.ColeccionDTO;
import backend.backend_v1.dto.Coleccion.ColeccionUpdateDTO;
import backend.backend_v1.dto.Producto.ProductoDTO;
import backend.backend_v1.exception.Coleccion.ColeccionAlreadyExistsException;
import backend.backend_v1.exception.Coleccion.ColeccionNotFoundException;
import backend.backend_v1.exception.Producto.ProductoNotFoundException;
import backend.backend_v1.mapper.ColeccionMapper;
import backend.backend_v1.mapper.ProductoMapper;
import backend.backend_v1.model.Coleccion;
import backend.backend_v1.repository.ColeccionRepository;


@Service 
public class ColeccionService {

    // Inyección de dependencias

    private final ColeccionRepository coleccionRepository;
    private final ColeccionMapper coleccionMapper;
    private final ProductoService productoService;
    private final ProductoMapper productoMapper;

    public ColeccionService(ColeccionRepository coleccionRepository, ColeccionMapper coleccionMapper, ProductoService productoService, ProductoMapper productoMapper) {
        this.coleccionRepository = coleccionRepository;
        this.coleccionMapper = coleccionMapper;
        this.productoService = productoService;
        this.productoMapper = productoMapper;

    } 

    // Método para obtener una colección por id

    @Transactional(readOnly = true)
    public ColeccionDTO obtenerColeccionPorId(Long coleccion_id) throws ColeccionNotFoundException {
        Coleccion coleccion = coleccionRepository.findById(coleccion_id)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con id: " + coleccion_id));

        return coleccionMapper.toDTO(coleccion);

    }

    // Método para crear una nueva colección

    @Transactional
    public ColeccionDTO crearNuevaColeccion(ColeccionCreateDTO coleccionCreate) throws ColeccionAlreadyExistsException {
        if(coleccionRepository.existsByNombre(coleccionCreate.getNombre())) {
            throw new ColeccionAlreadyExistsException("La colección con nombre: " + coleccionCreate.getNombre() + " ya existe. Cambia el nombre.");

        } // if

        Coleccion coleccionNueva = coleccionMapper.toEntityFromCreateDTO(coleccionCreate);
        return coleccionMapper.toDTO(coleccionRepository.saveAndFlush(coleccionNueva));

    }

    // Método para modificar una colección existente

    @Transactional
    public ColeccionDTO modificarColeccionExistente(Long coleccion_id, ColeccionUpdateDTO coleccionUpdate) throws ColeccionNotFoundException, ColeccionAlreadyExistsException {
        Coleccion coleccionActual = coleccionRepository.findById(coleccion_id)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con id: " + coleccion_id));

        // Obtener la entidad a partir de la entidad actual con los datos modificados, devolver el objeto nuevo.

        Coleccion coleccionModificada = coleccionMapper.toEntityFromUpdateDTO(coleccionMapper.toDTO(coleccionActual), coleccionUpdate);
        return coleccionMapper.toDTO(coleccionRepository.saveAndFlush(coleccionModificada));

    }

    // Método para buscar una colección por nombre

    @Transactional(readOnly = true)
    public ColeccionDTO obtenerColeccionPorNombre(String nombre) throws ColeccionNotFoundException {
        Coleccion coleccion = coleccionRepository.findByNombre(nombre)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con el nombre: " + nombre));

        return coleccionMapper.toDTO(coleccion);


    }

    // Método que permite añadir un producto nuevo a una colección existente

    @Transactional
    public boolean anyadirProductoAColeccion(Long coleccion_id, Long producto_id) throws ColeccionNotFoundException, ProductoNotFoundException {
        
        // Obtener la colección y el producto para añadirlos posteriormente
        
        Coleccion coleccion = coleccionRepository.findById(coleccion_id)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con id: " + coleccion_id));

        Producto producto = productoMapper.toEntity(productoService.obtenerProductoPorId(producto_id));

        // Obtener los productos de la colección actual y añadir el nuevo 

        coleccion.getProductos().add(producto);
        coleccionRepository.saveAndFlush(coleccion);
        return true;

    }

    // Método para quitar un producto de una colección existente

    @Transactional
    public boolean quitarProductoDeColeccion(Long coleccion_id, Long producto_id) throws ColeccionNotFoundException, ProductoNotFoundException {

        // Obtener la colección y el producto para quitarlos posteriormente 

        Coleccion coleccion = coleccionRepository.findById(coleccion_id)
            .orElseThrow(() -> new ColeccionNotFoundException("No se ha encontrado ninguna colección con id: " + coleccion_id));

        // Obtener la colección con productos y quitar el que hemos obtenido a partir del id

        Producto productoEliminar = coleccion.getProductos()
            .stream()
            .filter(p -> p.getId().equals(producto_id))
            .findFirst()
            .orElseThrow(() -> new ProductoNotFoundException("No se ha encontrado producto con id: " + producto_id + " en la lista."));

        coleccion.getProductos().remove(productoEliminar);
        if(!coleccion.getProductos().contains(productoEliminar)) return true;
        else return false;

    }

    // Método para obtener todas las colecciones

    @Transactional(readOnly = true)
    public List<ColeccionDTO> obtenerTodasLasColecciones() {
        List<Coleccion> colecciones = coleccionRepository.findAll();
        List<ColeccionDTO> coleccionesDTOs = new ArrayList<ColeccionDTO>();
        for(Coleccion coleccion : colecciones) coleccionesDTOs.add(coleccionMapper.toDTO(coleccion));
        return coleccionesDTOs;

    }

} // class