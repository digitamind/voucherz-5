package com.interswitch.discountvoucherz.api.model.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
public class UpdateVoucherDetails {
    private Double value;
    private String expiryDate;
    private Boolean isActive;
    private String additionalInfo;
    private String category;
}
