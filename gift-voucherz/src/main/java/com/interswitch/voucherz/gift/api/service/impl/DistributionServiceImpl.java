package com.interswitch.voucherz.gift.api.service.impl;

import com.interswitch.voucherz.gift.api.model.distribution.VoucherMail;
import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
import com.interswitch.voucherz.gift.api.queue.GiftVoucherDistPublisher;
import com.interswitch.voucherz.gift.api.service.DistributionService;
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
    GiftVoucherDistPublisher publisher;

    @Async
    @Override
    public void sendStandaloneVoucher(GiftVoucher voucher) {
        VoucherMail mail = buildMail(voucher);
        publisher.publish(mail);

    }

    @Async
    @Override
    public void sendBulkVoucher(List<GiftVoucher> vouchers) {
        List<VoucherMail> mailList = new ArrayList<>();
        VoucherMail mail;
        for(GiftVoucher voucher: vouchers){
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

    public VoucherMail buildMail(GiftVoucher voucher){
        VoucherMail mail = new VoucherMail();
        mail.setVoucherCode(voucher.getCode());
        mail.setEmailAddress(voucher.getCustomerId());
        mail.setVoucherDescription("USE THIS PROMO CODE TO SHOP ON OUR WEBSITE: ");
        mail.setVoucherType(1);
        return mail;
    }
}
