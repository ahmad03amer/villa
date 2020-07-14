package com.exalt.villaRentalSystem.errorAPI;


import org.springframework.http.HttpStatus;

public class InputNotValid extends ApiBaseException {
    private String exceptionMsg;

    public InputNotValid(String exceptionMsg){
        super(exceptionMsg);
    }
    @Override
    public HttpStatus getStatusCode(){
        return HttpStatus.NOT_FOUND;
    }
}
