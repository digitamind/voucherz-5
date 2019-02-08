package com.interswitch.valuevoucherz.api.config;

import com.interswitch.valuevoucherz.api.queue.VoucherDistPublisher;
import com.interswitch.valuevoucherz.api.queue.publisher.impl.VoucherDistPublisherImpl;
import com.interswitch.valuevoucherz.api.queue.subscriber.DeleteCampaignVoucherSubscriber;
import com.interswitch.valuevoucherz.api.service.VoucherService;
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
    private VoucherService service;

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
    VoucherDistPublisher giftVoucherDistPublisher() {
        return new VoucherDistPublisherImpl(redisTemplate(),
                new ChannelTopic("pubsub:distribute-value-voucher"));
    }

    @Bean
    MessageListenerAdapter deleteVoucherListener() {
        return new MessageListenerAdapter(new DeleteCampaignVoucherSubscriber(service));
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(deleteVoucherListener(), deleteVoucherTopic());
        return container;
    }

    @Bean
    ChannelTopic deleteVoucherTopic() {
        return new ChannelTopic("pubsub:value-voucher-delete");
    }
}
