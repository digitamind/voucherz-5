package com.interswitch.voucherz.authservice.config;
import com.interswitch.voucherz.authservice.dao.JwtTokenDao;
import com.interswitch.voucherz.authservice.models.DUserDetail;
import com.interswitch.voucherz.authservice.models.JwtToken;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;

@Component
public class JwtTokenProvider {

    private static final String AUTH="auth";
    private static final String MerchantId = "merchantId";
    private static final String AUTHORIZATION="Authorization";
    private String secretKey="secret-key";

    private long validityInMilliseconds = 180000000; // 1h

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Autowired
    private JwtTokenDao jwtTokenDao;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles, long merchantId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(AUTH, roles);
        //todo: insert merchant id in the token
        claims.put(MerchantId, merchantId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token =  Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
        jwtTokenDao.save(new JwtToken(token));
        return token;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        logger.info(bearerToken);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token) throws JwtException,IllegalArgumentException{
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return true;
    }

    public boolean isTokenPresentInDB (String token) {
        return jwtTokenDao.findById(token);
    }
    //user details with out database hit
    public UserDetails getUserDetails(String token) {
        String userName =  getUsername(token);
        List<String> roleList = getRoleList(token);
        UserDetails userDetails = new DUserDetail(userName,roleList.toArray(new String[roleList.size()]));
        return userDetails;
    }

    public List<String> getRoleList(String token) {
        return (List<String>) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).
                getBody().get(AUTH);
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Long getMerchantId(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return (long) claims.get(MerchantId);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails =  userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
