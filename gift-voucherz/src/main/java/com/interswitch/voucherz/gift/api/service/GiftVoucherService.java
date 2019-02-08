package com.interswitch.voucherz.gift.api.service;

import com.interswitch.voucherz.gift.api.model.Page;
import com.interswitch.voucherz.gift.api.model.request.BulkVouchers;
import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
import com.interswitch.voucherz.gift.api.model.response.Response;

import java.util.List;


public interface GiftVoucherService<T>{
    void createSingleVoucher(GiftVoucher voucher);

    void createBulk(BulkVouchers model);

    T getVoucherByCode(T model);

    List<T> getVoucherByMerchantUser(T model);

    List<T> getVoucherByCampaign(T model);

    List<T> getVoucherByDateCreated(T model);

    List<T> getVoucherByActiveStatus(T model);

    List<T> getVoucherByExpiryDate(T model);

    T validateVoucher(T model);

    Response update(T model);

    Page<T> getAll(int pageNumber, int pageSize);

    Response delete(T voucher);

    List<T> getVoucherByCustomer(T model);

    List<T> getVoucherByProduct(T model);

    T redeem(T model);

    T addBalance(T model);

    Response enable(T model);

    Response disable(T model);

    Response unDelete(T model);

//    Payment payment(Payment model);
}
