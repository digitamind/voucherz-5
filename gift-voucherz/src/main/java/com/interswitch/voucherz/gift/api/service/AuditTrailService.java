package com.interswitch.voucherz.gift.api.service;


import com.interswitch.voucherz.gift.api.util.EventType;

public interface AuditTrailService {
    public void publishAudit(String actor, EventType eventType, String description);
}
