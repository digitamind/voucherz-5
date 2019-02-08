package com.interswitch.voucherz.authservice.config;


import com.interswitch.voucherz.authservice.queue.producer.*;
import com.interswitch.voucherz.authservice.queue.producer.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        template.afterPropertiesSet();
        return template;
    }


    @Bean
    VerificationMailPublisher verificationMailPublisher() {
        return new VerificationMailPublisherImpl(redisTemplate(),
                new ChannelTopic("pubsub:verification-mail"));
    }

    @Bean
    AuditEventPublisher auditEventPublisher(){
        return new AuditEventPublisherImpl(redisTemplate(), new ChannelTopic("pubsub:audit-trail"));
    }

    @Bean
    CampaignDiscountDeletePublisher campaignDiscountDeletePublisher(){
        return new CampaignDiscountDeletePublisherImp(redisTemplate(),
                new ChannelTopic("pubsub:discount-voucher-delete"));
    }

    @Bean
    CampaignGiftDeletePublisher campaignGiftDeletePublisher(){
        return new CampaignGiftDeletePublisherImp(redisTemplate(),
                new ChannelTopic("pubsub:gift-voucher-delete"));
    }

    @Bean
    CampaignValueDeletePublisher campaignValueDeletePublisher(){
        return new CampaignValueDeletePublisherImp(redisTemplate(),
                new ChannelTopic("pubsub:value-voucher-delete"));
    }


//
//    @Bean
//    ChannelTopic verificationMailtopic() {
//        return new ChannelTopic("pubsub:verifiation-mail");
//    }

//    @Bean
//    ChannelTopic auditTrailTopic() {
//        return new ChannelTopic("pubsub:audit-trail");
//    }

}
