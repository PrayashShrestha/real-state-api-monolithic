package miu.ea.realestateapimonolithic.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class MismatchException extends RuntimeException{
    private final String message;
}
