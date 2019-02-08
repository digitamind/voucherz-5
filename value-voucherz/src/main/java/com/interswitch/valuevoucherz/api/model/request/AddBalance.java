package com.interswitch.valuevoucherz.api.model.request;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AddBalance{
    private BigInteger amount;
}
