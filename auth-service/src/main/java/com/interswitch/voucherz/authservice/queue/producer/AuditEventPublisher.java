package com.interswitch.voucherz.authservice.queue.producer;

import redis.clients.jedis.exceptions.JedisConnectionException;

import java.net.ConnectException;

public interface AuditEventPublisher{
    void publish(Object event);
}
