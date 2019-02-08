package com.interswitch.valuevoucherz.api.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
public class DeleteVoucher{
    @JsonProperty("setCode")
    private String code;
}
