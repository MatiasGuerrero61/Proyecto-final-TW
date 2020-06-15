package ar.edu.unlam.tallerweb1.excepciones;

public class TiendaNotFoundException extends RuntimeException {

    public TiendaNotFoundException(String message) {
        super(message);
    }
}
