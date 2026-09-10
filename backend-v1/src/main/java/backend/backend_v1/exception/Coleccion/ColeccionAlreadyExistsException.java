package backend.backend_v1.exception.Coleccion;

public class ColeccionAlreadyExistsException extends RuntimeException {

    public ColeccionAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

}
