package com.interswitch.discountvoucherz.api.service.impl;

import com.interswitch.discountvoucherz.api.model.distribution.VoucherMail;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import com.interswitch.discountvoucherz.api.queue.DiscountVoucherDistPublisher;
import com.interswitch.discountvoucherz.api.service.DistributionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DistributionServiceImpl implements DistributionService {

    @Autowired
    DiscountVoucherDistPublisher publisher;

    @Async
    @Override
    public void sendStandaloneVoucher(DiscountVoucher voucher) {
        VoucherMail mail = buildMail(voucher);
        try{
            publisher.publish(mail);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }

    }

    @Async
    @Override
    public void sendBulkVoucher(List<DiscountVoucher> vouchers) {
        List<VoucherMail> mailList = new ArrayList<>();
        VoucherMail mail;
        for(DiscountVoucher voucher: vouchers){
            mail = buildMail(voucher);
            mailList.add(mail);
        }
        try{
            publisher.publish(mailList);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    public VoucherMail buildMail(DiscountVoucher voucher){
        VoucherMail mail = new VoucherMail();
        mail.setVoucherCode(voucher.getCode());
        mail.setEmailAddress(voucher.getCustomerId());
        mail.setVoucherDescription("USE THIS PROMO CODE TO SHOP ON OUR WEBSITE: ");
        mail.setVoucherType(1);
        return mail;
    }
}
