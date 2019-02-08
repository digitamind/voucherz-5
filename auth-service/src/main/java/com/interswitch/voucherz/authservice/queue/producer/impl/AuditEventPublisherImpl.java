package com.interswitch.voucherz.authservice.queue.producer.impl;

import com.interswitch.voucherz.authservice.controller.MerchantController;
import com.interswitch.voucherz.authservice.queue.producer.AuditEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.net.ConnectException;

public class AuditEventPublisherImpl implements AuditEventPublisher{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    private ChannelTopic topic;
    private static final Logger logger = LoggerFactory.getLogger(AuditEventPublisherImpl.class);

    @Autowired
    public AuditEventPublisherImpl() {
    }

    public AuditEventPublisherImpl(final RedisTemplate<String, Object> redisTemplate, final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
//        this.topic = topic;
    }

    @Override
    public void publish(Object event){
        try{
            redisTemplate.convertAndSend(new ChannelTopic("pubsub:audit-trail").getTopic(), event);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
