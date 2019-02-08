package com.interswitch.voucherz.authservice.queue.producer;

import com.interswitch.voucherz.authservice.models.VerificationMail;

public interface VerificationMailPublisher {
    void publish(Object verificationMail);
}
