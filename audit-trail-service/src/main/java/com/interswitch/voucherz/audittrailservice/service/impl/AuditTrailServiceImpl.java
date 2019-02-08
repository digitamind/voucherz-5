package com.interswitch.voucherz.audittrailservice.service.impl;

import com.interswitch.voucherz.audittrailservice.model.AuditEvent;
import com.interswitch.voucherz.audittrailservice.repository.AuditEventRepository;
import com.interswitch.voucherz.audittrailservice.service.AuditTrailService;
import com.interswitch.voucherz.audittrailservice.util.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditTrailServiceImpl implements AuditTrailService {

    @Autowired
    AuditEventRepository auditEventRepository;

    @Override
    public void saveAudit(AuditEvent event) {
        auditEventRepository.save(event);
    }

    @Override
    public List<AuditEvent> findByEventType(EventType eventType) {
        return auditEventRepository.findByEventType(eventType);
    }

    @Override
    public Page<AuditEvent> findByEventType(EventType eventType, Pageable pageable) {
        return auditEventRepository.findByEventType(eventType, pageable);
    }

    @Override
    public List<AuditEvent> findAll() {
        return auditEventRepository.findAll();
    }

    @Override
    public Page<AuditEvent> findAllEvents(Pageable pageable) {
        return auditEventRepository.findAll(pageable);
    }

    @Override
    public long getCountAudits() {
        return auditEventRepository.count();
    }

}
