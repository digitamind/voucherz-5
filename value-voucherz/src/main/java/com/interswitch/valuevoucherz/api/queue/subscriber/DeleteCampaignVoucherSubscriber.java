package com.interswitch.valuevoucherz.api.queue.subscriber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.valuevoucherz.api.model.request.DeleteVoucher;
import com.interswitch.valuevoucherz.api.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class DeleteCampaignVoucherSubscriber implements MessageListener {

    @Autowired
    private VoucherService service;

    public DeleteCampaignVoucherSubscriber(VoucherService service){
        this.service = service;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            DeleteVoucher voucher = jsonMapper.readValue(message.toString(), DeleteVoucher.class);
            //service.delete(voucherCode);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
