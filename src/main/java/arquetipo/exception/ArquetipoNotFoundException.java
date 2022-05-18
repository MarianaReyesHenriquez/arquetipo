package arquetipo.exception;

public class ArquetipoNotFoundException extends RuntimeException {

    public ArquetipoNotFoundException(Long id) {
        super("No se ha podido encontrar el arquetipo: " + id);
    }
    
}
