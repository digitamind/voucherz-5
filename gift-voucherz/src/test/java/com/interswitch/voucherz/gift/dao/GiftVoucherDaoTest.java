//package com.interswitch.voucherz.gift.dao;
//
//import com.interswitch.voucherz.gift.api.dao.GiftVoucherDao;
//import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
//import com.interswitch.voucherz.library.model.CodeConfig;
//import com.interswitch.voucherz.library.utils.VoucherCodeGenerator;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class GiftVoucherDaoTest {
//
//    @Autowired
//    GiftVoucherDao<GiftVoucher> giftVoucherDao;
//
//    @Test
//    public void createStandaloneVoucher(){
//        GiftVoucher voucher = new GiftVoucher();
//        CodeConfig config = CodeConfig.builder().length(7).build();
//        voucher.setCode(VoucherCodeGenerator.generate(config));
//        voucher.setUserId("ola@gmail.com");
//        voucher.setMerchantId("1");
//        voucher.setAmount(200);
//        voucher.setProductId("1");
//        giftVoucherDao.createSingleVoucher(voucher);
//    }
//}
