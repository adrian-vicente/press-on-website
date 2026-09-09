package backend.backend_v1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import backend.backend_v1.exception.Producto.ProductoAlreadyExistsException;

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

}