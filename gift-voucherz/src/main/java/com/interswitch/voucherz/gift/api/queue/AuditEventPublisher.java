package com.interswitch.voucherz.gift.api.queue;

public interface AuditEventPublisher{
    void publish(Object event);
}
