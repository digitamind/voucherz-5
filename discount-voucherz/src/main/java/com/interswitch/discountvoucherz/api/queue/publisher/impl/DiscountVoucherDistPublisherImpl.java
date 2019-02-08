package com.interswitch.discountvoucherz.api.queue.publisher.impl;

import com.interswitch.discountvoucherz.api.queue.DiscountVoucherDistPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;


public class DiscountVoucherDistPublisherImpl implements DiscountVoucherDistPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public DiscountVoucherDistPublisherImpl(){

    }

    public DiscountVoucherDistPublisherImpl(final RedisTemplate<String, Object> redisTemplate,
                                            final ChannelTopic topic){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(Object distributeVouchers) {
        redisTemplate.convertAndSend(new ChannelTopic("pubsub:distribute-discount-voucher")
                .getTopic(), distributeVouchers);
    }
}
