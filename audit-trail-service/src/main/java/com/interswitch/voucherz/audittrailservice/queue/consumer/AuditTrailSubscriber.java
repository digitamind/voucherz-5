package com.interswitch.voucherz.audittrailservice.queue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.audittrailservice.model.AuditEvent;
import com.interswitch.voucherz.audittrailservice.service.AuditTrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class AuditTrailSubscriber implements MessageListener {

    @Autowired
    private AuditTrailService auditTrailService;

    public AuditTrailSubscriber(AuditTrailService auditTrailService){
        this.auditTrailService = auditTrailService;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            AuditEvent auditEvent = jsonMapper.readValue(message.toString(), AuditEvent.class);
            auditTrailService.saveAudit(auditEvent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
