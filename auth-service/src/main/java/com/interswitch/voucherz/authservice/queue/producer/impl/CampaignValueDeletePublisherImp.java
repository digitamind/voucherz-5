package com.interswitch.voucherz.authservice.queue.producer.impl;

import com.interswitch.voucherz.authservice.queue.producer.CampaignValueDeletePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class CampaignValueDeletePublisherImp implements CampaignValueDeletePublisher {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CampaignValueDeletePublisherImp(){

    }

    public CampaignValueDeletePublisherImp(final RedisTemplate<String, Object> redisTemplate,
                                          final ChannelTopic topic){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(Object campaignVoucherDelete) {
        redisTemplate.convertAndSend(new ChannelTopic("pubsub:value-voucher-delete")
                .getTopic(), campaignVoucherDelete);
    }
}
