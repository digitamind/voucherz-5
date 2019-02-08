package com.interswitch.voucherz.gift.api.exception;

import org.springframework.http.HttpStatus;

public class RequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;
    private final String message;

    public RequestException(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public RequestException(String message) {
        this.message = message;
        this.httpStatus = null;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
