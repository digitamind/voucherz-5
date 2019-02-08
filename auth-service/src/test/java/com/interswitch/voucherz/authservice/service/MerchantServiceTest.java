package com.interswitch.voucherz.authservice.service;

import com.interswitch.voucherz.authservice.dao.MerchantDao;
import com.interswitch.voucherz.authservice.models.BaseEntity;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MerchantServiceTest{

    @Autowired
    private MerchantService merchantService;

    @MockBean
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

        Mockito.when(merchantDao.findByEmail(user.getEmail()))
                .thenReturn(user);

    }

    @Test
    public void whenValidEmail_thenMerchantShouldBeFound() {
        MerchantUser merchant = merchantService.findMerchantByUsername(email);
        Assert.assertNotNull(merchant);
    }


}
