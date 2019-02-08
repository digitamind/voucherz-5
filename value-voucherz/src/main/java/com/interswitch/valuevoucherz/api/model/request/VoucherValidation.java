package com.interswitch.valuevoucherz.api.model.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
public class VoucherValidation{
    private String code;
    private ValidationStatus status;
    private Integer orderAmount;
    private String productGuid;
    private String customerGuid;
}
