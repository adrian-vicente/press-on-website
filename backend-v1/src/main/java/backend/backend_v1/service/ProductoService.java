package backend.backend_v1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.backend_v1.dto.Producto.ProductoCreateDTO;
import backend.backend_v1.dto.Producto.ProductoDTO;
import backend.backend_v1.exception.Producto.ProductoAlreadyExistsException;
import backend.backend_v1.mapper.ProductoMapper;
import backend.backend_v1.mapper.UsuarioMapper;
import backend.backend_v1.model.Producto;
import backend.backend_v1.repository.ColeccionRepository;
import backend.backend_v1.repository.ProductoRepository;

@Service 
public class ProductoService {

    // Inyección de dependencias

    private final ProductoMapper productoMapper;
    private final ColeccionRepository coleccionRepository;
    private final ColeccionService coleccionService;
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoMapper productoMapper, ColeccionRepository coleccionRepository, ColeccionService coleccionService, ProductoRepository productoRepository) {
        this.productoMapper = productoMapper;
        this.coleccionRepository = coleccionRepository;
        this.coleccionService = coleccionService;
        this.productoRepository = productoRepository;
    }

    // Método para obtener un producto por id

    // Método para crear un nuevo producto 

    @Transactional
    public ProductoDTO crearProducto(ProductoCreateDTO productoCreate) throws ProductoAlreadyExistsException {
        if(productoRepository.existsByNombre(productoCreate.getNombre())) {
            throw new ProductoAlreadyExistsException("Ya existe un producto con el nombre: " + productoCreate.getNombre());

        } else {
            Producto productoNuevo = productoMapper.toEntityFromCreateDTO(productoCreate);
            return productoMapper.toDTO( productoRepository.saveAndFlush(productoNuevo) );

        } // if - else

    } // crearProducto()

    // Método para modificar un producto existente

    // Método para buscar un producto por nombre

    // Método para obtener una lista de productos por nombre de colección

    // Método para obtener todos los productos

} // class