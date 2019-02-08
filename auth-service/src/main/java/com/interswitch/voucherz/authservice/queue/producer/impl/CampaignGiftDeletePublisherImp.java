package com.interswitch.voucherz.authservice.queue.producer.impl;

import com.interswitch.voucherz.authservice.queue.producer.CampaignGiftDeletePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class CampaignGiftDeletePublisherImp implements CampaignGiftDeletePublisher {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public CampaignGiftDeletePublisherImp(){

    }

    public CampaignGiftDeletePublisherImp(final RedisTemplate<String, Object> redisTemplate,
                                              final ChannelTopic topic){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void publish(Object campaignVoucherDelete) {
        redisTemplate.convertAndSend(new ChannelTopic("pubsub:gift-voucher-delete")
                .getTopic(), campaignVoucherDelete);
    }
}
