package com.interswitch.discountvoucherz.api.service;


import com.interswitch.discountvoucherz.api.model.Page;
import com.interswitch.discountvoucherz.api.model.Response;
import com.interswitch.discountvoucherz.api.model.request.BulkVouchers;

import java.util.List;


public interface DiscountVoucherService<T>{
    Response createBulk(BulkVouchers model);

    Response createSingleVoucher(T model);

    T getVoucherByCode(T model);

    List<T> getVoucherByMerchantUser(T model);

    List<T> getVoucherByCampaign(T model);

    List<T> getVoucherByDateCreated(T model);

    List<T> getVoucherByActiveStatus(T model);

    List<T> getVoucherByExpiryDate(T model);

    T validateVoucher(T model);

    Response update(T model);

    Page<T> findAll(int pageNumber, int pageSize);

    Response delete(T voucher);

    List<T> getVoucherByCustomer(T model);

    List<T> getVoucherByProduct(T model);

    T redeem(T model);

    T addBalance(T model);

    Response enable(T model);

    Response disable(T model);

    Response unDelete(T model);

}
