package com.interswitch.valuevoucherz.api.model.request;

import com.interswitch.valuevoucherz.api.model.UserCredential;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
public class VoucherRequest {
    private String code;
    private UserCredential credential;
}
