//package com.interswitch.voucherz.gift.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.interswitch.voucherz.gift.api.dao.GiftVoucherDao;
//import com.interswitch.voucherz.gift.api.dao.impl.GiftVoucherDaoImpl;
//import com.interswitch.voucherz.gift.api.model.request.GiftVoucher;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.io.IOException;
//
//@RunWith(SpringRunner.class)
////@ActiveProfiles("test")
//@EnableConfigurationProperties
//@SpringBootTest
//@AutoConfigureMockMvc(secure = false)
////@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
////@TestPropertySource(locations = "classpath:application-test.properties")
//public class GiftVoucherControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @TestConfiguration
//    static class GiftVoucherControllerTestContextConfiguration {
//
//        @Bean
//        public GiftVoucherDao<GiftVoucher> giftVoucherDao() {
//            return new GiftVoucherDaoImpl<>();
//        }
//    }
//
//    @Autowired
//    private GiftVoucherDao<GiftVoucher> giftVoucherDao;
//
//    @Test
//    public void whenValidInputThenCreateVoucher() throws IOException, Exception {
//        GiftVoucher voucher = new GiftVoucher();
//        voucher.setMerchantId("1");
//        voucher.setUserId("ola@gmail.com");
//        voucher.setCode("CtduYrcdW");
//
//        mvc.perform(MockMvcRequestBuilders.post("/gift-voucher")
//                .content(asJsonString(voucher))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        GiftVoucher giftVoucher = giftVoucherDao.getVoucherByCode(voucher);
//        Assert.assertNotNull(giftVoucher);
//    }
//
//
//    public static String asJsonString(final Object obj) {
//        try {
//            final ObjectMapper mapper = new ObjectMapper();
//            final String jsonContent = mapper.writeValueAsString(obj);
//            return jsonContent;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
