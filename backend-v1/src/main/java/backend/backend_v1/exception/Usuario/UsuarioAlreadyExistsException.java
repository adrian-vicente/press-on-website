package backend.backend_v1.exception.Usuario;

public class UsuarioAlreadyExistsException extends RuntimeException {

    public UsuarioAlreadyExistsException(String mensaje) {
        super(mensaje);
    }

} // class