package com.interswitch.voucherz.gift.api.model.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Response {
    private final HttpStatus code;
    private final String description;
    private final List<Error> errors;
}
