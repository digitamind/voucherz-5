package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.dao.impl.CampaignDaoImpl;
import com.interswitch.voucherz.authservice.dao.impl.MerchantDaoImpl;
import com.interswitch.voucherz.authservice.models.Campaign;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CampaignDaoTest {

    @TestConfiguration
    static class MerchantDaoImplTestContextConfiguration {

        @Bean
        public CampaignDao campaignDao() {
            return new CampaignDaoImpl();
        }

        @Bean
        public MerchantDao merchantDao(){ return new MerchantDaoImpl(); }
    }

    @Autowired
    private CampaignDao campaignDao;

    @Autowired
    private  MerchantDao merchantDao;

    @Test
    public void whenValidCampaignData_thenCampaignShouldBeCreated(){
        MerchantUser user = createMerchantForCampaignCreation();
        Campaign campaign = new Campaign();
        campaign.setName("Campaign Promo2");
        campaign.setDescription("Lorem Ipsum");
        campaign.setActivate(0);
        campaign.setStartDate(new Date());
        campaign.setExpirationDate(new Date());
        campaign.setVoucherType(1);
        campaign.setMerchantUserId(user.getId());
        campaign.setMerchantId(user.getMerchantId());

        campaignDao.create(campaign);
        Campaign getCampaign = campaignDao.getCampaignByName(campaign.getName(), user.getMerchantId());
        Assert.assertNotNull(getCampaign);
    }

    public MerchantUser createMerchantForCampaignCreation(){
        MerchantUser user = new MerchantUser();
        user.setCompanySize(100);
        user.setCompanyName("Aladeusi");
        user.setFirstName("Bamiji");
        user.setLastName("OLL");
        user.setEmail("victortemitope95@gmail.com");
        user.setPassword("$2a$10$7/fZCZfxJSRmtvGNiiLBGOm.6591mV7Ziu1Sm67uDjOtlQUR/9hP6");
        user.setIsCorporate(1);
        user.setMerchantId(null);
        user.setRole("ADMIN");

        merchantDao.create(user);
        return merchantDao.findByEmail("victortemitope95@gmail.com");
    }

}
