package ar.edu.unlam.tallerweb1.excepciones;

public class AnuncioNotFoundException extends RuntimeException{

    public AnuncioNotFoundException(String message) {
        super(message);
    }
}
