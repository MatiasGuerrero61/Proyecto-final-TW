package ar.edu.unlam.tallerweb1.excepciones;

public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(String message) {
        super(message);
    }
}
