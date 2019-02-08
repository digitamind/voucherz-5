package com.interswitch.voucherz.authservice.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.service.MerchantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.CoreMatchers.is;

import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@EnableConfigurationProperties
@SpringBootTest
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
//@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc(secure = false)
@ContextConfiguration
@WebAppConfiguration
public class MerchantControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MerchantService merchantService;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    public void whenPostMerchant_thenCreateMerchant()
            throws Exception{
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
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(201)));

    }

    @Test
    public void whenEmailIsPresent_rejectCreate() throws Exception{
        MerchantUser user = new MerchantUser();
        user.setCompanySize(100);
        user.setCompanyName("Aladeusi");
        user.setFirstName("Bamiji");
        user.setLastName("OLL");
        user.setEmail("swissvic95@gmail.com");
        user.setPassword("$2a$10$7/fZCZfxJSRmtvGNiiLBGOm.6591mV7Ziu1Sm67uDjOtlQUR/9hP6");
        user.setIsCorporate(1);
        user.setMerchantId(null);
        user.setRole("ADMIN");

        BDDMockito.given(merchantService.findMerchantByUsername("swissvic95@gmail.com"))
                .willReturn(user);

        mvc.perform(MockMvcRequestBuilders.post("/merchants/signup")
                .content(asJsonString(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code", is(409)));
        BDDMockito.verify(merchantService, VerificationModeFactory.times(1))
                .findMerchantByUsername(Mockito.any());

        BDDMockito.reset(merchantService);
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
