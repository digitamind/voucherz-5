package com.interswitch.discountvoucherz.api.model.request;

import lombok.Data;

import java.math.BigInteger;

@Data
public class RedeemVoucher {
    private Integer orderAmount;
}
