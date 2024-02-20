package it.danielrrapi.U5W3D2.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'entità con id: " + id + " non è stata trovata");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
