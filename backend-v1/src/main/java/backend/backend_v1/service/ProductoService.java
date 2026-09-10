package backend.backend_v1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.backend_v1.dto.Producto.ProductoCreateDTO;
import backend.backend_v1.dto.Producto.ProductoDTO;
import backend.backend_v1.dto.Producto.ProductoUpdateDTO;
import backend.backend_v1.exception.Producto.ProductoAlreadyExistsException;
import backend.backend_v1.exception.Producto.ProductoNotFoundException;
import backend.backend_v1.mapper.ProductoMapper;
import backend.backend_v1.model.Producto;
import backend.backend_v1.repository.ProductoRepository;

@Service 
public class ProductoService {

    // Inyección de dependencias

    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoMapper productoMapper, ProductoRepository productoRepository) {
        this.productoMapper = productoMapper;
        this.productoRepository = productoRepository;
    }

    // Método para obtener un producto por id

    @Transactional(readOnly = true)
    public ProductoDTO obtenerProductoPorId(Long producto_id) throws ProductoNotFoundException {
        Producto producto = productoRepository.findById(producto_id)
            .orElseThrow(() -> new ProductoNotFoundException("No se ha encontrado ningún producto con id: " + producto_id));

        return productoMapper.toDTO(producto);

    }

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

    @Transactional 
    public ProductoDTO modificarProductoExistente(Long productoOriginal_id, ProductoUpdateDTO productoUpdate) throws ProductoAlreadyExistsException, ProductoNotFoundException {
        
        // Obtener el producto original a partir del id pasado cómo parámetro
        
        Producto productoOriginal = productoRepository.findById(productoOriginal_id)
            .orElseThrow(() -> new ProductoNotFoundException("No se ha encontrado ningún producto con el id: " + productoOriginal_id));

        // Comprobar si existe un producto con el nombre que se quiere utilizada

        if(productoRepository.existsByNombre(productoUpdate.getNombre())) {
            throw new ProductoAlreadyExistsException("Ya existe un producto con el nombre: " + productoUpdate.getNombre());

        } // if

        // Obtener la entidad con la información modificada a través del mapper

        Producto productoModificado = productoMapper.toEntityFromUpdateDTO(productoMapper.toDTO(productoOriginal), productoUpdate);

        // Guardar y actualizar la información del producto devolviendo el DTO modificado

        return productoMapper.toDTO( productoRepository.saveAndFlush(productoModificado) );

    }

    // Método para buscar un producto por nombre

    @Transactional(readOnly = true)
    public ProductoDTO encontrarPorNombre(String nombre) throws ProductoNotFoundException {
        Producto producto = productoRepository.findByNombre(nombre)
            .orElseThrow(() -> new ProductoNotFoundException("No se ha encontrado ningún producto con el nombre: " + nombre));

        return productoMapper.toDTO(producto);

    }

    // Método para obtener una lista de productos por nombre de colección

    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProductosFiltradosColeccion(String nombreColeccion) {
        List<Producto> productos = productoRepository.findByColeccionesNombre(nombreColeccion);
        List<ProductoDTO> productoDTOs = new ArrayList<ProductoDTO>();
        for(Producto prod : productos) productoDTOs.add(productoMapper.toDTO(prod));
        return productoDTOs;

    }

    // Método para obtener todos los productos

    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTodosLosProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productoDTOs = new ArrayList<ProductoDTO>();
        for(Producto prod : productos) productoDTOs.add(productoMapper.toDTO(prod));
        return productoDTOs;

    } 

} // class