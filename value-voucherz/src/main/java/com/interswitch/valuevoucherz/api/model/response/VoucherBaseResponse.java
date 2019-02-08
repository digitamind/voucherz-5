package com.interswitch.valuevoucherz.api.model.response;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
public class VoucherBaseResponse extends BaseResponse {
    private String code;
    private String campaignGuid;
    private String categoryGuid;
    private Timestamp dateCreated;
    private Timestamp expirationDate;
    private Boolean isActive;
    private String additionalInfo;
}
