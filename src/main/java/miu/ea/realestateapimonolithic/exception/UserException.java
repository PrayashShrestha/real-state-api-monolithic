package miu.ea.realestateapimonolithic.exception;

public class UserException extends RuntimeException{
    private String message;

    public UserException(String message){
        super(message);
    }
}
