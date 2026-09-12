package backend.backend_v1.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend_v1.dto.Coleccion.ColeccionCreateDTO;
import backend.backend_v1.dto.Coleccion.ColeccionDTO;
import backend.backend_v1.dto.Coleccion.ColeccionUpdateDTO;
import backend.backend_v1.exception.Coleccion.ColeccionAlreadyExistsException;
import backend.backend_v1.exception.Coleccion.ColeccionNotFoundException;
import backend.backend_v1.exception.Producto.ProductoNotFoundException;
import backend.backend_v1.service.ColeccionService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/colecciones")
public class ColeccionRestController {

    // Inyección de dependencias

    private final ColeccionService coleccionService;
    public ColeccionRestController(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }

    // Método para obtener una colección a partir del id

    @GetMapping("/obtener/{coleccion_id}")
    public ResponseEntity<ColeccionDTO> obtenerColeccionId(@PathVariable Long coleccion_id) throws ColeccionNotFoundException {
        return ResponseEntity.ok(coleccionService.obtenerColeccionPorId(coleccion_id));

    }

    // Método para crear una nueva colección

    @PreAuthorize("hasRole('USUARIO')")
    @PostMapping("/crear")
    public ResponseEntity<ColeccionDTO> crearNuevaColeccion(@RequestBody @Valid ColeccionCreateDTO coleccionCreate) throws ColeccionAlreadyExistsException {
        return ResponseEntity.ok(coleccionService.crearNuevaColeccion(coleccionCreate));

    }

    // Método para obtener listado de todas las colecciones

    @GetMapping
    public ResponseEntity<List<ColeccionDTO>> obtenerTodasLasColecciones() {
        return ResponseEntity.ok(coleccionService.obtenerTodasLasColecciones());
        
    }

    // Método que permite modificar una colección existente 
    
    @PreAuthorize("hasRole('USUARIO')")
    @PutMapping("/modificar/{coleccion_id}")
    public ResponseEntity<ColeccionDTO> modificarColeccionExistente(@PathVariable Long coleccion_id, @RequestBody @Valid ColeccionUpdateDTO coleccionUpdate) throws ColeccionAlreadyExistsException, ColeccionNotFoundException {
        return ResponseEntity.ok(coleccionService.modificarColeccionExistente(coleccion_id, coleccionUpdate));
        
    }

    // Método para obtener una colección por nombre

    @GetMapping("/obtener/coleccion/{coleccion_nombre}")
    public ResponseEntity<ColeccionDTO> obtenerColeccionNombre(@PathVariable String coleccion_nombre) throws ColeccionNotFoundException {
        return ResponseEntity.ok(coleccionService.obtenerColeccionPorNombre(coleccion_nombre));

    }

    // Método que permite añadir un producto nuevo a una colección existente 

    @PreAuthorize("hasRole('USUARIO')")
    @PostMapping("/{coleccion_id}/productos/{producto_id}")
    public ResponseEntity<String> anyadirProductoAColeccion(@PathVariable Long coleccion_id, @PathVariable Long producto_id) throws ColeccionNotFoundException, ProductoNotFoundException {
        if(coleccionService.anyadirProductoAColeccion(coleccion_id, producto_id)) {
            return ResponseEntity.ok("El producto con id: " + producto_id + " se ha añadido correctamente a la colección.");

        } else return ResponseEntity.ok("Algo ha fallado en el proceso, habla con el administrador del sistema.");

    }

    // Método que permite quitar un producto de una colección existente 
    
    @PreAuthorize("hasRole('USUARIO')")
    @DeleteMapping("/{coleccion_id}/productos/{producto_id}")
    public ResponseEntity<String> quitarProductoDeColeccion(@PathVariable Long coleccion_id, @PathVariable Long producto_id) throws ColeccionNotFoundException, ProductoNotFoundException {
        if(coleccionService.quitarProductoDeColeccion(coleccion_id, producto_id)) {
            return ResponseEntity.ok("El producto con id: " + producto_id + " se ha quitado correctamente de la colección");

        } else return ResponseEntity.ok("Algo ha fallado en el proceso para eliminar el producto de la colección, contacta con el administrador del sistema.");

    }

} // class