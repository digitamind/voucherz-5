package com.interswitch.voucherz.gift.api.model.request;

import lombok.Data;

@Data
public class Payment{
    private String billerName;
    private Integer amount;
    private String voucher;
}
