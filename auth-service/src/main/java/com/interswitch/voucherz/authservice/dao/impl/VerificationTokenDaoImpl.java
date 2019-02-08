package com.interswitch.voucherz.authservice.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.authservice.dao.VerificationTokenDao;
import com.interswitch.voucherz.authservice.models.JwtToken;
import com.interswitch.voucherz.authservice.models.VerificationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;

@Repository
public class VerificationTokenDaoImpl implements VerificationTokenDao {
    private static final String KEY = "Verification-Token";
    private final Jedis jedis;

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, VerificationToken> hashOperations;

    private static final Logger logger = LoggerFactory.getLogger(VerificationTokenDaoImpl.class);

    @Autowired
    public VerificationTokenDaoImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.jedis = new Jedis();
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void save(VerificationToken token) {
        hashOperations.put(KEY, token.getToken(), token);
    }

    @Override
    public VerificationToken findByToken(String verificationToken) {
        logger.info("-----------------------");
        ObjectMapper jsonMapper = new ObjectMapper();

        try{
            VerificationToken getToken = jsonMapper.convertValue(hashOperations.get(KEY, verificationToken),
                    VerificationToken.class);

            if (getToken == null){
                return null;
            }

            return  getToken;
        }
        catch(Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return null;
        }

    }

    @Override
    public void delete(VerificationToken token) {
        hashOperations.delete(KEY, token.getToken());
    }
}
