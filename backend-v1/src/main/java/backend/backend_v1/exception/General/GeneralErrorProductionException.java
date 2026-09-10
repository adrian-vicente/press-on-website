package backend.backend_v1.exception.General;

public class GeneralErrorProductionException extends RuntimeException {

    public GeneralErrorProductionException(String mensaje) {
        super(mensaje);
    }

}
