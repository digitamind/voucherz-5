package com.interswitch.voucherz.notificationservice.config;

import com.interswitch.voucherz.notificationservice.queue.consumer.BulkVoucherMailDistributionSubscriber;
import com.interswitch.voucherz.notificationservice.queue.consumer.SingleVoucherMailDistributionSubscriber;
import com.interswitch.voucherz.notificationservice.queue.consumer.VerificationMailSubscriber;
import com.interswitch.voucherz.notificationservice.service.EmailService;
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

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Autowired
    private EmailService emailService;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    MessageListenerAdapter verificationMailListener() {
        return new MessageListenerAdapter(new VerificationMailSubscriber(emailService));
    }

    @Bean
    MessageListenerAdapter singleDiscountVoucherMailListener() {
        return new MessageListenerAdapter(new SingleVoucherMailDistributionSubscriber(emailService));
    }

    @Bean
    MessageListenerAdapter bulkDiscountVoucherMailListener() {
        return new MessageListenerAdapter(new BulkVoucherMailDistributionSubscriber(emailService));
    }


    @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(verificationMailListener(), verificationMailTopic());
        container.addMessageListener(singleDiscountVoucherMailListener(), singleDiscountVoucherMailTopic());
        container.addMessageListener(bulkDiscountVoucherMailListener(), bulkDiscountVoucherMailTopic());
        return container;
    }

    @Bean
    ChannelTopic verificationMailTopic() {
        return new ChannelTopic("pubsub:verification-mail");
    }

    @Bean
    ChannelTopic singleDiscountVoucherMailTopic() {
        return new ChannelTopic("pubsub:distribute-discount-voucher");
    }

    @Bean
    ChannelTopic bulkDiscountVoucherMailTopic() {
        return new ChannelTopic("pubsub:distribute-discount-voucher-bulk");
    }
}
