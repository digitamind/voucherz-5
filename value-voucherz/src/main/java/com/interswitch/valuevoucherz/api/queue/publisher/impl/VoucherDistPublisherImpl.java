package com.interswitch.valuevoucherz.api.queue.publisher.impl;

import com.interswitch.valuevoucherz.api.queue.VoucherDistPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;


public class VoucherDistPublisherImpl implements VoucherDistPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public VoucherDistPublisherImpl(){

    }

    public VoucherDistPublisherImpl(final RedisTemplate<String, Object> redisTemplate,
                                    final ChannelTopic topic){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(Object distributeGiftVoucher) {
        redisTemplate.convertAndSend(new ChannelTopic("pubsub:distribute-gift-voucherCode")
                .getTopic(), distributeGiftVoucher);
    }
}
