package backend.backend_v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import backend.backend_v1.exception.Coleccion.ColeccionAlreadyExistsException;
import backend.backend_v1.exception.Coleccion.ColeccionNotFoundException;
import backend.backend_v1.exception.General.GeneralErrorProductionException;
import backend.backend_v1.exception.Producto.ProductoAlreadyExistsException;
import backend.backend_v1.exception.Producto.ProductoNotFoundException;
import backend.backend_v1.exception.Usuario.UsuarioAlreadyExistsException;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductoAlreadyExistsException.class)
    public ResponseEntity<String> productoAlreadyExists(ProductoAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ex.getMessage());

    } // ProductoAlreadyExistsException

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> usuarioNoEncontrado(UsernameNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
            
    }

    @ExceptionHandler(UsuarioAlreadyExistsException.class)
    public ResponseEntity<String> usuarioAlreadyExists(UsuarioAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ex.getMessage());
            
    }

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<String> productoNotFound(ProductoNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
            
    }

    @ExceptionHandler(ColeccionNotFoundException.class)
    public ResponseEntity<String> coleccionNotFound(ColeccionNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
            
    }

    @ExceptionHandler(ColeccionAlreadyExistsException.class)
    public ResponseEntity<String> coleccionAlreadyExists(ColeccionAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ex.getMessage());
            
    }

    @ExceptionHandler(GeneralErrorProductionException.class)
    public ResponseEntity<String> generalErrorException(GeneralErrorProductionException ex) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ex.getMessage());
            
    }

}