package com.interswitch.voucherz.authservice.controller.model;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Response  {
    private final HttpStatus status;
    private final int code;
    private final String codeDescription;
    private final List<Error> errors;

    public int getCode() {
        return code;
    }
    public HttpStatus getStatus(){
        return status;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public Response(HttpStatus status,  int code, String description, List<Error> errors) {
        this.status = status;
        this.code = code;
        this.codeDescription = description;
        this.errors = errors;
    }
}
