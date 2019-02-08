package com.interswitch.discountvoucherz.api.service;

import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface DistributionService {
    @Async
    void sendStandaloneVoucher(DiscountVoucher voucher);

    @Async
    void sendBulkVoucher(List<DiscountVoucher> vouchers);
}
