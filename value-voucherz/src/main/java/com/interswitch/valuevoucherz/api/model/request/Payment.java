package com.interswitch.valuevoucherz.api.model.request;

import com.interswitch.valuevoucherz.api.model.BaseEntity;
import lombok.Data;

@Data
public class Payment extends BaseEntity {
    private String billerName;
    private Integer amount;
    private String voucherCode;
}
