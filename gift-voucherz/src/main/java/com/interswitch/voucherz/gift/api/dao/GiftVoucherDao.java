package com.interswitch.voucherz.gift.api.dao;
import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;

import java.util.List;

public interface GiftVoucherDao<T> extends BaseDao<T>{
    boolean createBulk(SQLServerDataTable model);

    boolean createSingleVoucher(GiftVoucher model);

    T getVoucherByCode(T model);

    List<T> getVoucherByMerchantUser(T model);

    List<T> getVoucherByCustomer(T model);

    List<T> getVoucherByCampaign(T model);

    List<T> getVoucherByDateCreated(T model);

    List<T> getVoucherByActiveStatus(T model);

    List<T> getVoucherByExpiryDate(T model);

    T validateVoucher(T model);

    List<T> getVoucherByProduct(T model);

    T redeem(T model);

    T addBalance(T model);

    boolean enable(T model);

    boolean disable(T model);

    boolean unDelete(T model);
}
