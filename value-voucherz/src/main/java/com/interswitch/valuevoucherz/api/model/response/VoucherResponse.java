package com.interswitch.valuevoucherz.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
@ToString
public class VoucherResponse {
    @JsonProperty("Total Records")
    private Integer total;
    private String status;
}
