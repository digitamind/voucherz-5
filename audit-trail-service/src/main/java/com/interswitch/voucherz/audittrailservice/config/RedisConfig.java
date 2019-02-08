package com.interswitch.voucherz.audittrailservice.config;

import com.interswitch.voucherz.audittrailservice.queue.consumer.AuditTrailSubscriber;
import com.interswitch.voucherz.audittrailservice.service.AuditTrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfig {

    @Autowired
    private AuditTrailService auditTrailService;

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
    MessageListenerAdapter auditTrailListener() {
        return new MessageListenerAdapter(new AuditTrailSubscriber(auditTrailService));
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(auditTrailListener(), auditTrailTopic());
        return container;
    }


    @Bean
    ChannelTopic auditTrailTopic() {
        return new ChannelTopic("pubsub:audit-trail");
    }



}
