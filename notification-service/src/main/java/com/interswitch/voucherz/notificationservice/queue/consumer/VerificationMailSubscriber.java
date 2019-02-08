package com.interswitch.voucherz.notificationservice.queue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.notificationservice.model.VerificationMail;
import com.interswitch.voucherz.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class VerificationMailSubscriber implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(VerificationMailSubscriber.class);

    @Autowired
    private EmailService emailService;

    public VerificationMailSubscriber(EmailService emailService){
        this.emailService = emailService;
    }

    @Override
    public void onMessage(Message message, byte[] bytes)  {
        ObjectMapper jsonMapper = new ObjectMapper();
        try{
            VerificationMail verificationMail = jsonMapper.readValue(message.toString(), VerificationMail.class);
            logger.info("Sending Mail");
            logger.info(verificationMail.toString());
            logger.info(verificationMail.getRecipientAddress());
            logger.info(verificationMail.getMessage());
            logger.info(verificationMail.getSubject());
            emailService.sendSimpleMessage(verificationMail.getRecipientAddress(),
                    verificationMail.getSubject(), verificationMail.getMessage());
            logger.info("Mail Sent");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
