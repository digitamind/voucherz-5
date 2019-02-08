package com.interswitch.voucherz.authservice.config;

import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        //MerchantUser user = merchantService.findMerchantByUsernameAndPassword(name, password);

        MerchantUser user = merchantService.findMerchantByUsername(name);

        if (user == null){
            logger.error("user not found");
            return null;
        }

        if(user.isEnabled() == 0){
            return null;
        }

        boolean isCorrect = passwordEncoder.matches(password, user.getPassword());

        if(!isCorrect){
            logger.error("Password incorrect");
            return null;
        }

        Authentication auth = new
                UsernamePasswordAuthenticationToken(name, password);
        return auth;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
