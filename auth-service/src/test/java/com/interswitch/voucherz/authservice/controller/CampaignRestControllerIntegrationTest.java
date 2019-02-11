//package com.interswitch.voucherz.authservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.interswitch.voucherz.authservice.dao.CampaignDao;
//import com.interswitch.voucherz.authservice.dao.MerchantDao;
//import com.interswitch.voucherz.authservice.dao.impl.CampaignDaoImpl;
//import com.interswitch.voucherz.authservice.dao.impl.MerchantDaoImpl;
//import com.interswitch.voucherz.authservice.models.Campaign;
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
//public class CampaignRestControllerIntegrationTest {
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
//        @Bean
//        public CampaignDao campaignDao() {
//            return new CampaignDaoImpl();
//        }
//    }
//
//
//    @Autowired
//    private MerchantDao merchantDao;
//
//    @Autowired
//    private CampaignDao campaignDao;
//
//    @Test
//    public void whenValidInput_thenCreateCampaign() throws IOException, Exception {
//        MerchantUser merchantUser = createMerchantForCampaignCreation();
//        Campaign campaign = new Campaign();
//        campaign.setName("Campaign Promo2");
//        campaign.setDescription("Lorem Ipsum");
//        campaign.setActivate(0);
//        campaign.setStartDate(new Date());
//        campaign.setExpirationDate(new Date());
//        campaign.setVoucherType(1);
//        campaign.setMerchantUserId(merchantUser.getId());
//        campaign.setMerchantId(merchantUser.getMerchantId());
//
//        mvc.perform(MockMvcRequestBuilders.post("/campaigns")
//                .content(asJsonString(campaign))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//        Campaign campaign_ = campaignDao.getCampaignByName(campaign.getName(), merchantUser.getMerchantId());
//        Assert.assertNotNull(campaign_);
//
//
//    }
//
//    @Test
//    public void whenEmailIsPresent_rejectCreate() throws IOException, Exception {
//        MerchantUser merchantUser = createMerchantForCampaignCreation();
//        Campaign campaign = new Campaign();
//        campaign.setName("Campaign Promo2");
//        campaign.setDescription("Lorem Ipsum");
//        campaign.setActivate(0);
//        campaign.setStartDate(new Date());
//        campaign.setExpirationDate(new Date());
//        campaign.setVoucherType(1);
//        campaign.setMerchantUserId(merchantUser.getId());
//        campaign.setMerchantId(merchantUser.getMerchantId());
//
//        campaignDao.create(campaign);
//
//        mvc.perform(MockMvcRequestBuilders.post("/campaigns")
//                .content(asJsonString(campaign))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code", is(409)));
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
//
//}
