package com.interswitch.discountvoucherz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.discountvoucherz.api.dao.DiscountVoucherDao;
import com.interswitch.discountvoucherz.api.dao.impl.DiscountVoucherDaoImpl;
import com.interswitch.discountvoucherz.api.model.request.DiscountVoucher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class DiscountVoucherControllerTest {
    @Autowired
    private MockMvc mvc;

    @TestConfiguration
    static class GiftVoucherControllerTestContextConfiguration {

        @Bean
        public DiscountVoucherDao<DiscountVoucher> discountVoucherDao() {
            return new DiscountVoucherDaoImpl<>();
        }
    }

    @Autowired
    private DiscountVoucherDao<DiscountVoucher> discountVoucherDao;

    @Test
    public void whenValidInputThenCreateVoucher() throws IOException, Exception {
        DiscountVoucher voucher = new DiscountVoucher();
        voucher.setMerchantId("1");
        voucher.setUserId("ola@gmail.com");
        voucher.setCode("CtduYrcdW");

        mvc.perform(MockMvcRequestBuilders.post("/gift-voucher")
                .content(asJsonString(voucher))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        DiscountVoucher discountVoucher = discountVoucherDao.getVoucherByCode(voucher);
        Assert.assertNotNull(discountVoucher);
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
