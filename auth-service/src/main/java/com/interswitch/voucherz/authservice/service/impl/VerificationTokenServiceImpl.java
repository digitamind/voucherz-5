package com.interswitch.voucherz.authservice.service.impl;

import com.interswitch.voucherz.authservice.dao.VerificationTokenDao;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.models.VerificationMail;
import com.interswitch.voucherz.authservice.models.VerificationToken;
import com.interswitch.voucherz.authservice.queue.producer.VerificationMailPublisher;
import com.interswitch.voucherz.authservice.service.VerificationTokenService;
import com.interswitch.voucherz.authservice.util.TokenExpiration;
import com.interswitch.voucherz.authservice.util.VerificationMailType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private static final Logger logger = LoggerFactory.getLogger(VerificationTokenServiceImpl.class);

    @Autowired
    VerificationTokenDao verificationTokenDao;

    @Autowired
    VerificationMailPublisher verificationMailPublisher;

    private static final int EXPIRATION = 60 * 24;

    @Async
    @Override
    public void sendVerificationToken(MerchantUser user, VerificationMailType verificationMailType) {
        String token = UUID.randomUUID().toString();
        String subject = null;
        String confirmationUrl = null;

        if (verificationMailType == VerificationMailType.REGISTRATION_VERFICATION_MAIL){
            subject = "Registration Verification - Voucherz";
            confirmationUrl = "http://localhost:8180/v1/merchant-management/merchants" +
                    "/confirmRegistration?token="+token;
        }

        else if (verificationMailType == VerificationMailType.FORGOT_PASSWORD_VERIFICATION_MAIL){
            subject = "Forgot password - Voucherz";
            confirmationUrl = "http://localhost:8080/v1/merchant-management/merchants" +
                    "/password-reset-redirect?token="+token;
        }


        VerificationToken verificationToken = new VerificationToken();

        verificationToken.setExpiryDate(TokenExpiration.calculateExpiryDate(EXPIRATION));
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        try{
            verificationTokenDao.save(verificationToken);
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

        //create mail object
        VerificationMail verificationMail = new VerificationMail();
        verificationMail.setRecipientAddress(user.getEmail());
        verificationMail.setSubject(subject);

        //TODO: append the baseurl
        verificationMail.setMessage(confirmationUrl);

        try{
            verificationMailPublisher.publish(verificationMail);
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        logger.info(verificationToken);
        return verificationTokenDao.findByToken(verificationToken);
    }

    @Override
    public void deleteVerificationToken(VerificationToken token) {
        verificationTokenDao.delete(token);
    }
}
