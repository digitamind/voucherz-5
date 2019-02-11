package com.interswitch.valuevoucherz.api.service;

import com.interswitch.valuevoucherz.api.dao.VoucherDao;
import com.interswitch.valuevoucherz.api.exception.RequestException;
import com.interswitch.valuevoucherz.api.model.Page;
import com.interswitch.valuevoucherz.api.model.request.BulkVouchers;
import com.interswitch.valuevoucherz.api.model.request.Voucher;
import com.interswitch.valuevoucherz.api.model.response.VoucherResponse;
import com.interswitch.voucherz.library.model.CodeConfig;
import com.interswitch.voucherz.library.utils.VoucherCodeGenerator;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;

@Slf4j
@Service
public class VoucherServiceImpl<T> implements VoucherService<T> {

    @Autowired
    private VoucherDao<T> dao;

    @Override
    public T createSingleVoucher(T voucher){
        return dao.createStandaloneVoucher(voucher);
    }

    @Override
    public VoucherResponse createBulk(BulkVouchers bulkVouchers){
        SQLServerDataTable vouchersTvp = null;
        CodeConfig codeConfig = bulkVouchers.getCodeConfig();
        Voucher model = bulkVouchers.getVoucher();
        try {
            vouchersTvp = getTvpWithMetadata();
            addTableRows(model, vouchersTvp, codeConfig);
        } catch (SQLServerException e) {
            throw new RequestException(e.getClass().getName()+" : "+e.getMessage());
        }

        return dao.createBulk(vouchersTvp);
    }

    private SQLServerDataTable getTvpWithMetadata() throws SQLServerException {
        SQLServerDataTable vouchers;
        vouchers = new SQLServerDataTable();
        vouchers.setTvpName("[dbo].[voucher_table_param]");
        vouchers.addColumnMetadata("setCode", Types.NVARCHAR);
        vouchers.addColumnMetadata("amount", Types.BIGINT);
        vouchers.addColumnMetadata("dateCreated", Types.DATE);
        vouchers.addColumnMetadata("expiryDate", Types.DATE);
        vouchers.addColumnMetadata("isActive", Types.BIT);
        vouchers.addColumnMetadata("isCorporate", Types.BIT);
        vouchers.addColumnMetadata("category", Types.NVARCHAR);
        vouchers.addColumnMetadata("campaignId", Types.NVARCHAR);
        vouchers.addColumnMetadata("customerId", Types.NVARCHAR);
        vouchers.addColumnMetadata("merchantId", Types.NVARCHAR);
        vouchers.addColumnMetadata("userId", Types.NVARCHAR);
        vouchers.addColumnMetadata("merchantStoreId", Types.BIGINT);
        vouchers.addColumnMetadata("additionalInfo", Types.NVARCHAR);
        return vouchers;
    }

    private void addTableRows(Voucher model, SQLServerDataTable vouchers, CodeConfig config) throws SQLServerException {
        for(int i = 0; i< config.getQuantity() ; i++){

            vouchers.addRow(VoucherCodeGenerator.generate(config),
                    model.getAmount(),
                    model.getDateCreated(),
                    model.getExpiryDate(),
                    model.getIsActive(),
                    model.getIsCorporate(),
                    model.getCategory(),
                    model.getCampaignId(),
                    model.getCustomerId(),
                    model.getMerchantId(),
                    model.getUserId(),
                    model.getMerchantStoreId(),
                    model.getAdditionalInfo());
        }
    }

    @Override
    public T update(T model) {
        return dao.update(model);
    }

    @Override
    public List<T> getVoucherByMerchantUser(T model) {
        return dao.getVoucherByMerchantUser(model);
    }

    @Override
    public List<T> getVoucherByCampaign(T model) {
        return dao.getVoucherByCampaign(model);
    }

    @Override
    public List<T> getVoucherByDateCreated(T model) {
        return dao.getVoucherByDateCreated(model);
    }

    @Override
    public List<T> getVoucherByActiveStatus(T model) {
        return dao.getVoucherByActiveStatus(model);
    }

    @Override
    public List<T> getVoucherByExpiryDate(T model) {
        return dao.getVoucherByExpiryDate(model);
    }

    @Override
    public T validateVoucher(T model) {
        return dao.validateVoucher(model);
    }

    @Override
    public Boolean delete(T model) {
        return dao.delete(model);
    }

    @Override
    public List<T> getVoucherByCustomer(T model) {
        return dao.getVoucherByCustomer(model);
    }

    @Override
    public List<T> getVoucherByProduct(T model) {
        return dao.getVoucherByProduct(model);
    }

    @Override
    public T redeem(T model) {
        return dao.redeem(model);
    }

    @Override
    public T addBalance(T model) {
        return dao.addBalance(model);
    }

    @Override
    public T enable(T model) {
        return dao.enable(model);
    }

    @Override
    public T disable(T model) {
        return dao.disable(model);
    }

    @Override
    public T unDelete(T model) {
        return dao.unDelete(model);
    }


    @Override
    public Page<T> findAll(int pageNumber, int pageSize) {
        return dao.getAll(pageNumber,pageSize);
    }

    @Override
    public T getVoucherByCode(T request) {
        return dao.getVoucherByCode(request);
    }

}
