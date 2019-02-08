package com.interswitch.valuevoucherz.api.model.response;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddBalanceResponse {
    private Integer amount;
    private String object;
}
