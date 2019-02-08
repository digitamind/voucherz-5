package com.interswitch.valuevoucherz.api.model.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class Response {

    private final HttpStatus code;
    private final String description;
    private final List<Error> errors;


    public Response(HttpStatus code, String description, List<Error> errors) {
        this.code = code;
        this.description = description;
        this.errors = errors;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
