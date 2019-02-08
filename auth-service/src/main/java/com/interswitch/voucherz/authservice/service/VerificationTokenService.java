package com.interswitch.voucherz.authservice.service;

import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.models.VerificationToken;
import com.interswitch.voucherz.authservice.util.VerificationMailType;

public interface VerificationTokenService {
    void sendVerificationToken(MerchantUser user, VerificationMailType verificationMailType);
    VerificationToken getVerificationToken(String verificationToken);
    void deleteVerificationToken(VerificationToken token);
}
