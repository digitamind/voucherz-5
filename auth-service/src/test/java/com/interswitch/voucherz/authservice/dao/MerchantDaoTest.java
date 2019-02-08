package com.interswitch.voucherz.authservice.dao;

import com.interswitch.voucherz.authservice.dao.impl.MerchantDaoImpl;
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

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MerchantDaoTest {

    @TestConfiguration
    static class MerchantDaoImplTestContextConfiguration {

        @Bean
        public MerchantDao merchantDao() {
            return new MerchantDaoImpl();
        }
    }

    @Autowired
    private MerchantDao merchantDao;

    private String email = "victortemitope95@gmail.com";

    @Before
    public void setUp(){
        MerchantUser user = new MerchantUser();
        user.setCompanySize(100);
        user.setCompanyName("Aladeusi");
        user.setFirstName("Bamiji");
        user.setLastName("OLL");
        user.setEmail(email);
        user.setPassword("$2a$10$7/fZCZfxJSRmtvGNiiLBGOm.6591mV7Ziu1Sm67uDjOtlQUR/9hP6");
        user.setIsCorporate(1);
        user.setMerchantId(null);
        user.setRole("ADMIN");

        merchantDao.create(user);
    }

    @Test
    public void whenValidEmail_thenMerchantShouldBeFound() {
        MerchantUser fetchedMarchant = merchantDao.
                findByEmail(email);
        Assert.assertNotNull(fetchedMarchant);
    }


}
