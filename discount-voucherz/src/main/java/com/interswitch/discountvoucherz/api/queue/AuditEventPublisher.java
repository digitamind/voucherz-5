package com.interswitch.discountvoucherz.api.queue;

public interface AuditEventPublisher{
    void publish(Object event);
}
