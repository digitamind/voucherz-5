package com.interswitch.discountvoucherz.api.model.request;

public enum ValidationStatus {
    VALID,
    INVALID,
    DELETED,
    DISABLED;

    private ValidationStatus() {
    }
}