package com.interswitch.voucherz.audittrailservice.repository;

import com.interswitch.voucherz.audittrailservice.model.AuditEvent;

import com.interswitch.voucherz.audittrailservice.util.EventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditEventRepository extends MongoRepository<AuditEvent, String> {
    List<AuditEvent>  findByEventType(EventType eventType);
    Page<AuditEvent> findByEventType(EventType eventType, Pageable pageable);
    long count();
}
