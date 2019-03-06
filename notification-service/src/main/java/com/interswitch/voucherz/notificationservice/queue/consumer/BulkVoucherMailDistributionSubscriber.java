package com.interswitch.voucherz.notificationservice.queue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.notificationservice.model.VoucherMail;
import com.interswitch.voucherz.notificationservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.Arrays;
import java.util.List;

public class BulkVoucherMailDistributionSubscriber implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(BulkVoucherMailDistributionSubscriber.class);
    private EmailService emailService;

    public BulkVoucherMailDistributionSubscriber(EmailService emailService){
        this.emailService = emailService;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try{
           List<VoucherMail> voucherMailList = Arrays.asList(jsonMapper.
                   readValue(message.toString(), VoucherMail[].class));

           for(VoucherMail voucherMail : voucherMailList){
               emailService.sendSimpleMessage(voucherMail.getEmailAddress(),
                       "Voucher Mail", voucherMail.toString());
           }

        }
        catch(Exception exception){
            logger.info(exception.getMessage());
            exception.printStackTrace();
        }
    }
}
