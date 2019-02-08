package com.interswitch.discountvoucherz.api.queue.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.discountvoucherz.api.model.request.DeleteVoucher;
import com.interswitch.discountvoucherz.api.service.DiscountVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class DeleteCampaignVoucherSubscriber implements MessageListener {

    @Autowired
    private DiscountVoucherService discountVoucherService;

    public DeleteCampaignVoucherSubscriber(DiscountVoucherService discountVoucherService){
        this.discountVoucherService = discountVoucherService;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            DeleteVoucher voucher = jsonMapper.readValue(message.toString(), DeleteVoucher.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
