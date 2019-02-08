package com.interswitch.discountvoucherz.api.queue;

public interface DiscountVoucherDistPublisher {
    void publish(Object campaignVoucherDelete);
}
