package com.interswitch.voucherz.audittrailservice.service;

import com.interswitch.voucherz.audittrailservice.model.AuditEvent;
import com.interswitch.voucherz.audittrailservice.util.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuditTrailService {
    public void saveAudit(AuditEvent event);
    public List<AuditEvent> findByEventType(EventType eventType);
    public Page<AuditEvent> findByEventType(EventType eventType, Pageable pageable);
    public List<AuditEvent> findAll();
    public Page<AuditEvent> findAllEvents(Pageable pageable);
    public long getCountAudits();
}
