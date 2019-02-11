package com.interswitch.voucherz.library.exception;

public class RequestNotValidException extends RuntimeException{
    private String message;
    private Integer status;

    public RequestNotValidException(String message){
        this.message = message;
        this.status = 400;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
