//package com.interswitch.voucherz.authservice.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.interswitch.voucherz.authservice.dao.CampaignDao;
//import com.interswitch.voucherz.authservice.dao.CustomerDao;
//import com.interswitch.voucherz.authservice.dao.MerchantDao;
//import com.interswitch.voucherz.authservice.dao.impl.CampaignDaoImpl;
//import com.interswitch.voucherz.authservice.dao.impl.CustomerDaoImpl;
//import com.interswitch.voucherz.authservice.dao.impl.MerchantDaoImpl;
//import com.interswitch.voucherz.authservice.models.Campaign;
//import com.interswitch.voucherz.authservice.models.Customer;
//import com.interswitch.voucherz.authservice.models.MerchantUser;
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
//import java.util.Date;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@EnableConfigurationProperties
//@SpringBootTest
//@AutoConfigureMockMvc(secure = false)
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = "classpath:application-test.properties")
//public class CustomerRestControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @TestConfiguration
//    static class MerchantDaoImplTestContextConfiguration {
//
//        @Bean
//        public MerchantDao merchantDao() {
//            return new MerchantDaoImpl();
//        }
//
////        @Bean
////        public CustomerDao customerDao() {
////            return new CustomerDaoImpl();
////        }
//    }
//
//    @Autowired
//    private MerchantDao merchantDao;
////
////    @Autowired
////    private CustomerDao customerDao;
//
//
//    @Test
//    public void whenValidInput_thenCreateCampaign() throws IOException, Exception {
//        MerchantUser merchantUser = createMerchantForCampaignCreation();
//        Customer customer = new Customer();
//        customer.setFirstName("VIctor");
//        customer.setLastName("Abidoye");
//        customer.setAddress("lorem");
//        customer.setCity("lorem");
//        customer.setCountry("lorem");
//        customer.setPhoneNo("lorem");
//        customer.setEmail("lorem");
//        customer.setAmountOfOrders(90);
//        customer.setNumberOfOrders(90);
//        customer.setDateJoined(new Date());
//        customer.setLastOrderAmount(20);
//        customer.setLastOrderDate(new Date());
//        customer.setKpi(2);
//        customer.setMerchantId(merchantUser.getMerchantId());
//
//        mvc.perform(MockMvcRequestBuilders.post("/customers")
//                .content(asJsonString(customer))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code", is(201)));
//
//    }
//
//    public MerchantUser createMerchantForCampaignCreation(){
//        MerchantUser user = new MerchantUser();
//        user.setCompanySize(100);
//        user.setCompanyName("Aladeusi");
//        user.setFirstName("Bamiji");
//        user.setLastName("OLL");
//        user.setEmail("victortemitope95@gmail.com");
//        user.setPassword("$2a$10$7/fZCZfxJSRmtvGNiiLBGOm.6591mV7Ziu1Sm67uDjOtlQUR/9hP6");
//        user.setIsCorporate(1);
//        user.setMerchantId(null);
//        user.setRole("ADMIN");
//
//        merchantDao.create(user);
//        return merchantDao.findByEmail("victortemitope95@gmail.com");
//    }
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
