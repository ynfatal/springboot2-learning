package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter13ApplicationTests {

    @Autowired
    private RedisProperties properties;

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void fun1() {
        System.out.println(properties);
        System.out.println();
    }

    @Test
    public void fun2() {
        Duration timeout = properties.getTimeout();
        if (timeout != null) {
            int l = (int)timeout.toMillis();    // 10000
            System.out.println(l);
        }
    }

    @Test
    public void fun3() {
        System.out.println(jedisPool);
        System.out.println(jedisPool.getResource());
    }

}
