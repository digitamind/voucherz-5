package com.interswitch.discountvoucherz.api.model.request;

public enum DiscountType {

    AMOUNT(0), PERCENT(1), UNIT(2);
    private int value;

    private DiscountType(int value){
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}

