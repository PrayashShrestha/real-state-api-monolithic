package miu.ea.realestateapimonolithic.exception;

/*
    Using when doing validation such as duplicate email
 */
public class RecordAlreadyExistsException extends RuntimeException {

    public RecordAlreadyExistsException(final String message) {
        super(message);
    }
}
