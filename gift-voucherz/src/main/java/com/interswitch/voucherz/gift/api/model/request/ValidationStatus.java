package com.interswitch.voucherz.gift.api.model.request;

public enum ValidationStatus {
    VALID,
    INVALID,
    DELETED,
    DISABLED;

    private ValidationStatus() {
    }
}