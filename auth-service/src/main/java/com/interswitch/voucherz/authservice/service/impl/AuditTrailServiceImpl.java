package com.interswitch.voucherz.authservice.service.impl;

import com.interswitch.voucherz.authservice.models.AuditEvent;
import com.interswitch.voucherz.authservice.queue.producer.AuditEventPublisher;
import com.interswitch.voucherz.authservice.service.AuditTrailService;
import com.interswitch.voucherz.authservice.util.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    @Autowired
    private AuditEventPublisher auditEventPublisher;

    @Override
    public void publishAudit(String actor, EventType eventType, String description) {
        AuditEvent auditEvent = new AuditEvent();
        auditEvent.setUsername(actor);
        auditEvent.setEventDate(new Date());
        auditEvent.setEventType(eventType);
        auditEvent.setDescription(description);
        auditEventPublisher.publish(auditEvent);
    }
}
