package com.fatal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis 配置类
 * 单机版使用JedisPool
 * 集群版使用JedisCluster
 * @author: Fatal
 * @date: 2018/10/13 0013 13:44
 */
@Configuration
public class JedisConfig {

    @Autowired
    private RedisProperties properties;

    /**
     * JedisPool资源池
     */
    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(properties.getJedis().getPool().getMaxIdle());
        config.setMinIdle(properties.getJedis().getPool().getMinIdle());
        config.setMaxTotal(properties.getJedis().getPool().getMaxActive());
        config.setMaxWaitMillis(properties.getJedis().getPool().getMaxWait().toMillis());
        JedisPool pool = new JedisPool(config,
                properties.getHost(),
                properties.getPort(),
                (int)properties.getTimeout().toMillis(),
                properties.getPassword());
        return pool;
    }

}
