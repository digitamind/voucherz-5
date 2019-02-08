package com.interswitch.discountvoucherz.api.model.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Error {
    private final String field;
    private final String message;
}
