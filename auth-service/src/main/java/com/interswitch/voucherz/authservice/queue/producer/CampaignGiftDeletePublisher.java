package com.interswitch.voucherz.authservice.queue.producer;

public interface CampaignGiftDeletePublisher {
    void publish(Object campaignVoucherDelete);
}
