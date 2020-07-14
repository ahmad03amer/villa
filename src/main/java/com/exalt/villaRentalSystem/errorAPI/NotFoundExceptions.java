package com.exalt.villaRentalSystem.errorAPI;
import org.springframework.http.HttpStatus;

public class NotFoundExceptions extends ApiBaseException{
    public NotFoundExceptions(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode(){
        return HttpStatus.NOT_FOUND;
    }
}
