package backend.backend_v1.exception.Usuario;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String mensaje) {
        super(mensaje);
    }

}
