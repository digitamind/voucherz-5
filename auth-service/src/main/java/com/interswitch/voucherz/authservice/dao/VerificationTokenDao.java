package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.models.VerificationToken;

public interface VerificationTokenDao {
    void save(VerificationToken token);
    VerificationToken findByToken(String verificationToken);
    void delete(VerificationToken token);
}
