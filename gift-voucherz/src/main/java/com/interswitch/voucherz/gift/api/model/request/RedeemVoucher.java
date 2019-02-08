package com.interswitch.voucherz.gift.api.model.request;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RedeemVoucher {
    private Integer orderAmount;
    private String orderStatus;
}
