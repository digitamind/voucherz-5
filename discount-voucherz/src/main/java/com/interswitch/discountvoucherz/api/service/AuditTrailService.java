package com.interswitch.discountvoucherz.api.service;

import com.interswitch.discountvoucherz.api.util.EventType;

public interface AuditTrailService {
    public void publishAudit(String actor, EventType eventType, String description);
}
