package backend.backend_v1.exception.Producto;

public class ProductoNotFoundException extends RuntimeException{

    public ProductoNotFoundException(String mensaje) {
        super(mensaje);
        
    }

}
