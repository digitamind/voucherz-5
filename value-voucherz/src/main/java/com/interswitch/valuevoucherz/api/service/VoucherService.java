package com.interswitch.valuevoucherz.api.service;

import com.interswitch.valuevoucherz.api.model.Page;
import com.interswitch.valuevoucherz.api.model.request.BulkVouchers;
import com.interswitch.valuevoucherz.api.model.response.VoucherResponse;

import java.util.List;


public interface VoucherService<T>{
    VoucherResponse createBulk(BulkVouchers model);

    T createSingleVoucher(T model);

    T getVoucherByCode(T model);

    List<T> getVoucherByMerchantUser(T model);

    List<T> getVoucherByCampaign(T model);

    List<T> getVoucherByDateCreated(T model);

    List<T> getVoucherByActiveStatus(T model);

    List<T> getVoucherByExpiryDate(T model);

    T validateVoucher(T model);

    T update(T model);

    Page<T> findAll(int pageNumber, int pageSize);

    Boolean delete(T voucher);

    List<T> getVoucherByCustomer(T model);

    List<T> getVoucherByProduct(T model);

    T redeem(T model);

    T addBalance(T model);

    T enable(T model);

    T disable(T model);

    T unDelete(T model);
}
