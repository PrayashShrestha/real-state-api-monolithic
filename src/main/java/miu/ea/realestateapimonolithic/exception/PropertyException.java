package miu.ea.realestateapimonolithic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PropertyException extends Exception{
    private String message;

    public PropertyException(final String message){
        super(message);
    }
}
