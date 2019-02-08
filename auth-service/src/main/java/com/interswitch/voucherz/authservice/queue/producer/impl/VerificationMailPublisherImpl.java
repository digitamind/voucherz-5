package com.interswitch.voucherz.authservice.queue.producer.impl;

import com.interswitch.voucherz.authservice.queue.producer.VerificationMailPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class VerificationMailPublisherImpl implements VerificationMailPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public VerificationMailPublisherImpl() {
    }

    public VerificationMailPublisherImpl(final RedisTemplate<String, Object> redisTemplate,
                                         final ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        //this.topic = topic;
    }

    @Override
    public void publish(Object verificationMail) {
        redisTemplate.convertAndSend(new ChannelTopic("pubsub:verification-mail").getTopic(), verificationMail);
    }
}
