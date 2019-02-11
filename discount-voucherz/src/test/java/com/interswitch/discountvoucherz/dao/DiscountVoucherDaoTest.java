package com.interswitch.discountvoucherz.dao;

import com.interswitch.discountvoucherz.api.dao.DiscountVoucherDao;
import com.interswitch.discountvoucherz.api.model.request.BulkVouchers;
import com.interswitch.discountvoucherz.api.model.request.DiscountType;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import com.interswitch.voucherz.library.model.CodeConfig;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DiscountVoucherDaoTest{

    @Autowired
    private DiscountVoucherDao dao;

    @Test
    public void createSingleVoucher() {
        DiscountVoucher model = new DiscountVoucher();
        model.setCode("WE34");
        model.setType(DiscountType.UNIT);
        model.setValue(200);
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        model.setCampaignId("0");
        model.setProductId("0");
        model.setCustomerId("0");
        model.setIsActive(true);
       // dao.createSingleVoucher(model);
    }

    @Test
    public void getVoucherByCode() {
        DiscountVoucher model = new DiscountVoucher();
        model.setCode("ISW-QR55");
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        model.setType(DiscountType.UNIT);
        //dao.getVoucherByCode(model);
    }


    @Test
    public void getVoucherByMerchantUser() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        //dao.getVoucherByMerchantUser(model);
    }

    @Test
    public void getVoucherByCampaign() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        model.setCampaignId("0");
       // dao.getVoucherByCampaign(model);

    }


    @Test
    public void getVoucherByDateCreated() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        //model.setDateCreated(Timestamp.valueOf("2019-02-01"));
        //dao.getVoucherByDateCreated(model);

    }


    @Test
    public void getVoucherByActiveStatus() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        //model.setIsActive(true);
        //dao.getVoucherByActiveStatus(model);

    }

    @Test
    public void getVoucherByExpiryDate() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        //model.setExpiryDate(Timestamp.valueOf("2019-02-01"));
        //dao.getVoucherByExpiryDate(model);
    }

    @Test
    public void validateVoucher() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        //model.setCode("132fER");
        //dao.validateVoucher(model);
    }

    @Test
    public void update() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        //model.setCode("132fER");
       // dao.update(model);
    }
}
