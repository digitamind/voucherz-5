package com.interswitch.voucherz.authservice.service;

import com.interswitch.voucherz.authservice.util.EventType;

import java.util.Date;

public interface AuditTrailService {
    public void publishAudit(String actor, EventType eventType, String description);
}
