package com.interswitch.discountvoucherz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.discountvoucherz.api.dao.DiscountVoucherDao;
import com.interswitch.discountvoucherz.api.dao.impl.DiscountVoucherDaoImpl;
import com.interswitch.discountvoucherz.api.model.request.BulkVouchers;
import com.interswitch.discountvoucherz.api.model.request.DiscountType;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import com.interswitch.discountvoucherz.api.service.DiscountVoucherService;
import com.interswitch.voucherz.library.model.CodeConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class DiscountVoucherControllerTest {
    @Autowired
    private MockMvc mvc;

//    @TestConfiguration
//    static class GiftVoucherControllerTestContextConfiguration {
//
//        @Bean
//        public DiscountVoucherDao<DiscountVoucher> discountVoucherDao() {
//            return new DiscountVoucherDaoImpl<>();
//        }
//    }

    @Autowired
    private DiscountVoucherDao<DiscountVoucher> discountVoucherDao;

    @Autowired
    private DiscountVoucherService<DiscountVoucher> service;

    @Test
    public void whenValidInputThenCreateVoucher() throws IOException, Exception {
        BulkVouchers model = new BulkVouchers();
        CodeConfig config = CodeConfig.builder().length(8).quantity(1).build();
        DiscountVoucher voucher = new DiscountVoucher();
        voucher.setMerchantId("1");
        voucher.setUserId("ola@gmail.com");
        voucher.setCode("CtduYrcdW");
        voucher.setType(DiscountType.UNIT);
        voucher.setValue(20);

//        model.setCodeConfig(config);
//        model.setDiscountVoucher(voucher);
//        mvc.perform(MockMvcRequestBuilders.post("/gift-voucher")
//                .content(asJsonString(model))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        service.createBulk(model);
    }

    @Test
    public void whenValidRequestThenGetVoucher() throws IOException, Exception {
        DiscountVoucher voucher = new DiscountVoucher();
        voucher.setMerchantId("1");
        voucher.setUserId("ola@gmail.com");
        voucher.setType(DiscountType.UNIT);

//        mvc.perform(MockMvcRequestBuilders.get("/gift-voucher")
//                .content(asJsonString(voucher))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//        discountVoucherDao.getVoucherByMerchantUser(voucher);
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
