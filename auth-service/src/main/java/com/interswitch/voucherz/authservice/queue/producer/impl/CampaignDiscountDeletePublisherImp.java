package com.interswitch.voucherz.authservice.queue.producer.impl;

import com.interswitch.voucherz.authservice.queue.producer.CampaignDiscountDeletePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class CampaignDiscountDeletePublisherImp implements CampaignDiscountDeletePublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CampaignDiscountDeletePublisherImp(){

    }

    public CampaignDiscountDeletePublisherImp(final RedisTemplate<String, Object> redisTemplate,
                                              final ChannelTopic topic){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(Object campaignVoucherDelete) {
        redisTemplate.convertAndSend(new ChannelTopic("pubsub:discount-voucher-delete")
                .getTopic(), campaignVoucherDelete);
    }
}
