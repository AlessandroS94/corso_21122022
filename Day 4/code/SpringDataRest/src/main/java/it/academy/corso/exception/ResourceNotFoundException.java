package it.academy.corso.exception;

public class ResourceNotFoundException extends RuntimeException {

    // serve a garantire la corretta deserializzazione degli oggetti e a mantenere la
    // compatibilità tra diverse versioni della classe serializzata.
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}