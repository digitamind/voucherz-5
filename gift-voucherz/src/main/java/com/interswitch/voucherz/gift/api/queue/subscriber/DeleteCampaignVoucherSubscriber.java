package com.interswitch.voucherz.gift.api.queue.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.gift.api.model.request.DeleteVoucher;
import com.interswitch.voucherz.gift.api.service.GiftVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class DeleteCampaignVoucherSubscriber implements MessageListener {

    @Autowired
    private GiftVoucherService giftVoucherService;

    public DeleteCampaignVoucherSubscriber(GiftVoucherService giftVoucherService){
        this.giftVoucherService = giftVoucherService;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            DeleteVoucher voucher = jsonMapper.readValue(message.toString(), DeleteVoucher.class);
            //giftVoucherService.delete(voucher);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
