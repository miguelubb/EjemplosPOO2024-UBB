package ejemplos.excepciones.mvc.exception;

public class ContactoRuntimeException extends RuntimeException{
    public ContactoRuntimeException(String mensaje) {
        super(mensaje);
    }
}
