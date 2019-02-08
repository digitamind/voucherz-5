package com.interswitch.valuevoucherz.api.model.request;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
public class UpdateVoucher{
    private String code;
    private Timestamp expiryDate;
    private Boolean isActive;
    private String additionalInfo;
    private String category;
}

