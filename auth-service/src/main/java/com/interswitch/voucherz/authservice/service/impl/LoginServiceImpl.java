package com.interswitch.voucherz.authservice.service.impl;

import com.interswitch.voucherz.authservice.Exception.CustomException;
import com.interswitch.voucherz.authservice.config.JwtTokenProvider;
import com.interswitch.voucherz.authservice.controller.MerchantController;
import com.interswitch.voucherz.authservice.dao.JwtTokenDao;
import com.interswitch.voucherz.authservice.models.JwtToken;
import com.interswitch.voucherz.authservice.models.MerchantUser;
import com.interswitch.voucherz.authservice.service.LoginService;
import com.interswitch.voucherz.authservice.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private UserDao merchantDao;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private JwtTokenDao jwtTokenDao;

    private static final Logger logger = LoggerFactory.getLogger(MerchantController.class);

    @Override
    public String login(String username, String password) {
        try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password));
            MerchantUser user = merchantService.findMerchantByUsername(username);
            long merchantId = user.getMerchantId() != null ? user.getMerchantId() : 0;
            //User user = merchantDao.findByEmail(username);
            if (user == null || user.getRole() == null){
                throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
            }

            if (user.isEnabled() == 0){
                throw new CustomException("User not verified", HttpStatus.UNAUTHORIZED);
            }

            String u_role = user.getRole();
            List<String> roles = Arrays.asList(u_role);
            String token = jwtTokenProvider.createToken(username, roles, merchantId);
            logger.info("Our token is: "+token);
            return token;

        } catch (AuthenticationException e) {
            logger.error(e.getMessage());
            throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public boolean logout(String bearerToken) {
        String token = bearerToken.substring(7, bearerToken.length());
        jwtTokenDao.delete(new JwtToken(token));
        return true;
    }

    @Override
    public Boolean isValidToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    @Override
    public String createNewToken(String token) {
        String username = jwtTokenProvider.getUsername(token);
        List<String> roleList = jwtTokenProvider.getRoleList(token);

        String newToken =  jwtTokenProvider.createToken(username, roleList,
                jwtTokenProvider.getMerchantId(token));
        return newToken;
    }

    @Override
    public String getClaimFromToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7, bearerToken.length());

            JsonParser parser = JsonParserFactory.getJsonParser();
            Map<String, ?> tokenData = parser.parseMap(JwtHelper.decode(token).getClaims());

            return (String) tokenData.get("sub");

        }
        return null;
    }
}
