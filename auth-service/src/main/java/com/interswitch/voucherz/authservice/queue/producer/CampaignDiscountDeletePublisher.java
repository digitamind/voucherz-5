package com.interswitch.voucherz.authservice.queue.producer;

public interface CampaignDiscountDeletePublisher {
    void publish(Object campaignVoucherDelete);
}
