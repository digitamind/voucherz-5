package com.interswitch.voucherz.authservice.queue.producer;

public interface CampaignValueDeletePublisher {
    void publish(Object campaignVoucherDelete);
}
