package miu.ea.realestateapimonolithic.exception;

public class NotAuthorizedException extends RuntimeException{

    private  String message;

    public NotAuthorizedException(String message){
        super(message);
    }
}
