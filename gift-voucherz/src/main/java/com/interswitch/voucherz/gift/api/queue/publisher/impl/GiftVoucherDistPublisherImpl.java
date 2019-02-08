package com.interswitch.voucherz.gift.api.queue.publisher.impl;

import com.interswitch.voucherz.gift.api.queue.GiftVoucherDistPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;


public class GiftVoucherDistPublisherImpl implements GiftVoucherDistPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public GiftVoucherDistPublisherImpl(){

    }

    public GiftVoucherDistPublisherImpl(final RedisTemplate<String, Object> redisTemplate,
                                        final ChannelTopic topic){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(Object distributeGiftVoucher) {
        redisTemplate.convertAndSend(new ChannelTopic("pubsub:distribute-gift-voucher")
                .getTopic(), distributeGiftVoucher);
    }
}
