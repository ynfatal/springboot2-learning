package com.fatal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * Redis 配置类
 * @author: Fatal
 * @date: 2018/10/13 0013 14:52
 */
@Configuration
public class RedisConfig {

    /**
     * 自定义RedisTemplate模板
     */
    @Bean
    public RedisTemplate<String, Serializable> serializableRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        // key序列化
        template.setKeySerializer(new StringRedisSerializer());
        // value序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
