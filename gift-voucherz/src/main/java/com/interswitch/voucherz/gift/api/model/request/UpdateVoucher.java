package com.interswitch.voucherz.gift.api.model.request;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
public class UpdateVoucher{
    private Timestamp expiryDate;
    private Boolean isActive;
    private String additionalInfo;
    private String category;
    private Integer amount;
}


