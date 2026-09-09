package backend.backend_v1.exception.Producto;

public class ProductoAlreadyExistsException extends RuntimeException {

    public ProductoAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

}
