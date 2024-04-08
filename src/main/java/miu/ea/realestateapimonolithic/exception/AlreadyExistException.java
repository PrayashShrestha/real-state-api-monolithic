package miu.ea.realestateapimonolithic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistException extends RuntimeException{
    private String message;

    public AlreadyExistException(final String message){
        super(message);
    }
}