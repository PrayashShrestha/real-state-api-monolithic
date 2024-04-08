package miu.ea.realestateapimonolithic.exception;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(final String message) {
        super(message);
    }
}
