package com.interswitch.valuevoucherz.api.model;

import lombok.*;

@Data
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserCredential{
    private String merchantId;
    private String userId;
}
