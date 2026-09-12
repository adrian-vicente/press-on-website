package backend.backend_v1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_v1.dto.Producto.ProductoCreateDTO;
import backend.backend_v1.dto.Producto.ProductoDTO;
import backend.backend_v1.dto.Producto.ProductoUpdateDTO;
import backend.backend_v1.exception.Producto.ProductoAlreadyExistsException;
import backend.backend_v1.exception.Producto.ProductoNotFoundException;
import backend.backend_v1.service.ProductoService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/productos")
public class ProductoRestController {

    // Inyección de dependencias 

    private final ProductoService productoService;
    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Método para crear un nuevo producto 

    @PreAuthorize("hasRole('USUARIO')")
    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody @Valid ProductoCreateDTO productoCreate) throws ProductoAlreadyExistsException {
        ProductoDTO productoNuevo = productoService.crearProducto(productoCreate);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productoNuevo);

    } // crearProducto

    // Método para obtener un producto a partir del nombre 

    @PreAuthorize("hasRole('USUARIO')")
    @GetMapping("/obtener/{nombre}")
    public ResponseEntity<ProductoDTO> obtenerProductoNombre(@PathVariable String nombre) throws ProductoNotFoundException {
        return ResponseEntity.ok( productoService.encontrarPorNombre(nombre) );

    }

    // Método para actualizar un producto existente 

    @PreAuthorize("hasRole('USUARIO')")
    @PutMapping("/modificar/{producto_id}")
    public ResponseEntity<ProductoDTO> modificarProductoExistente(@PathVariable Long producto_id, @RequestBody @Valid ProductoUpdateDTO productoUpdate) throws ProductoAlreadyExistsException, ProductoNotFoundException {
        return ResponseEntity.ok( productoService.modificarProductoExistente(producto_id, productoUpdate) );

    }

    // Método que permite obtener todos los productos registrads

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerProductos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());

    }

    // Método para obtener lista de productos mediante nombre de colección

    @GetMapping("/obtener/coleccion/{nombreColeccion}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorColeccion(@PathVariable String nombreColeccion) {
        return ResponseEntity.ok(productoService.obtenerProductosFiltradosColeccion(nombreColeccion));

    }

} // class