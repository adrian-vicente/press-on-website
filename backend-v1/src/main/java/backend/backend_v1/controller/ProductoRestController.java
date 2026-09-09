package backend.backend_v1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_v1.dto.Producto.ProductoCreateDTO;
import backend.backend_v1.dto.Producto.ProductoDTO;
import backend.backend_v1.exception.Producto.ProductoAlreadyExistsException;
import backend.backend_v1.model.Producto;
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

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody @Valid ProductoCreateDTO productoCreate) throws ProductoAlreadyExistsException {
        ProductoDTO productoNuevo = productoService.crearProducto(productoCreate);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(productoNuevo);

    } // crearProducto

} // class