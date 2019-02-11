package com.interswitch.discountvoucherz.service;

import com.interswitch.discountvoucherz.api.model.request.BulkVouchers;
import com.interswitch.discountvoucherz.api.model.request.DiscountType;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import com.interswitch.discountvoucherz.api.service.DiscountVoucherService;
import com.interswitch.voucherz.library.model.CodeConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@ContextConfiguration
public class DiscountVoucherServiceTest {
    @Autowired
    DiscountVoucherService<DiscountVoucher> service;

    @Test
    public void createSingleVoucher() {
        BulkVouchers voucher = new BulkVouchers();
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
        voucher.setDiscountVoucher(model);
        CodeConfig config = CodeConfig.builder().quantity(1).length(9).build();
        voucher.setCodeConfig(config);
        Assert.assertNotNull(service.createBulk(voucher));
    }

    @Test
    public void getVoucherByCode() {
        DiscountVoucher model = new DiscountVoucher();
        model.setCode("ISW-QR55");
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        model = service.getVoucherByCode(model);
        System.out.println(model);
        Mockito.when(service.getVoucherByCode(model)).thenReturn(model);
    }


    @Test
    public void getVoucherByMerchantUser() {
        DiscountVoucher model = new DiscountVoucher();
        model.setMerchantId("0");
        model.setUserId("swissvic95@gmail.com");
        Mockito.when(service.getVoucherByCode(model)).thenReturn(model);

    }

    public void getVoucherByCampaign() {

    }


    public void getVoucherByDateCreated() {

    }


    public void getVoucherByActiveStatus() {

    }

    public void getVoucherByExpiryDate() {

    }

    public void validateVoucher() {

    }

    public void update() {

    }

    public void findAll() {
    }


    public void delete() {
    }

    public void getVoucherByCustomer() {

    }

    public void getVoucherByProduct() {
    }

    public void redeem() {

    }

    public void addBalance() {
    }


    public void enable(Object model) {

    }

    public void disable(Object model) {

    }

    public void unDelete(Object model) {

    }
}
