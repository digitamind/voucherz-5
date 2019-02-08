package com.interswitch.valuevoucherz.api.model.request;

public enum ValidationStatus {
    VALID,
    INVALID,
    DELETED,
    DISABLED;

    private ValidationStatus() {
    }
}