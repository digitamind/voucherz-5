package com.interswitch.voucherz.authservice.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.voucherz.authservice.dao.JwtTokenDao;
import com.interswitch.voucherz.authservice.models.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.filter.GenericFilterBean;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;

@Repository
public class JwtTokenDaoImpl implements JwtTokenDao {

    private static final String KEY = "Url";
    private final Jedis jedis;

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, JwtToken> hashOperations;

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenDaoImpl.class);

    @Autowired
    public JwtTokenDaoImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.jedis = new Jedis();
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public boolean findById(String token) {
        ObjectMapper jsonMapper = new ObjectMapper();

        try{
            JwtToken getToken = jsonMapper.convertValue(hashOperations.get(KEY, token),
                    JwtToken.class);
            if(getToken == null){
                return false;
            }
            return true;

        }
        catch(Exception e){
            logger.error(e.getMessage());
            return false;
        }

    }

    @Override
    public void save(JwtToken token) {
        hashOperations.put(KEY, token.getToken(),token);
    }

    @Override
    public void delete(JwtToken token) {
        hashOperations.delete(KEY, token.getToken());
    }
}
