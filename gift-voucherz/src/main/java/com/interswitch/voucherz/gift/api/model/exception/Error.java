package com.interswitch.voucherz.gift.api.model.exception;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
public class Error {
    private final String field;
    private final String message;
}
