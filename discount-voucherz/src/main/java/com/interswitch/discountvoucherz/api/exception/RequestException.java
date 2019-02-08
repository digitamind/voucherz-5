package com.interswitch.discountvoucherz.api.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class RequestException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;
}
