package com.interswitch.voucherz.gift.api.service;

import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface DistributionService {
    @Async
    void sendStandaloneVoucher(GiftVoucher voucher);

    @Async
    void sendBulkVoucher(List<GiftVoucher> vouchers);
}
