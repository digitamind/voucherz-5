package com.interswitch.voucherz.authservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.authservice.dao.MerchantDao;
import com.interswitch.voucherz.authservice.dao.impl.MerchantDaoImpl;
import com.interswitch.voucherz.authservice.models.MerchantUser;
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

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@EnableConfigurationProperties
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class MerchantRestControllerIntegration {

    @Autowired
    private MockMvc mvc;

    @TestConfiguration
    static class MerchantDaoImplTestContextConfiguration {

        @Bean
        public MerchantDao merchantDao() {
            return new MerchantDaoImpl();
        }
    }

    @Autowired
    private MerchantDao merchantDao;

    @Test
    public void whenValidInput_thenCreateMerchant() throws IOException, Exception {
        MerchantUser user = new MerchantUser();
        user.setCompanySize(100);
        user.setCompanyName("Aladeusi");
        user.setFirstName("Bamiji");
        user.setLastName("OLL");
        user.setEmail("swisss92@gmail.com");
        user.setPassword("$2a$10$7/fZCZfxJSRmtvGNiiLBGOm.6591mV7Ziu1Sm67uDjOtlQUR/9hP6");
        user.setIsCorporate(1);
        user.setMerchantId(null);
        user.setRole("ADMIN");

        mvc.perform(MockMvcRequestBuilders.post("/merchants/signup")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        MerchantUser merchantUser = merchantDao.findByEmail(user.getEmail());
        Assert.assertNotNull(merchantUser);
    }

    @Test
    public void whenEmailIsPresent_rejectCreate() throws Exception{
        MerchantUser user = new MerchantUser();
        user.setCompanySize(100);
        user.setCompanyName("Aladeusi");
        user.setFirstName("Bamiji");
        user.setLastName("OLL");
        user.setEmail("abcdef@gmail.com");
        user.setPassword("$2a$10$7/fZCZfxJSRmtvGNiiLBGOm.6591mV7Ziu1Sm67uDjOtlQUR/9hP6");
        user.setIsCorporate(1);
        user.setMerchantId(null);
        user.setRole("ADMIN");

        merchantDao.create(user);

        mvc.perform(MockMvcRequestBuilders.post("/merchants/signup")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(409)));

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
