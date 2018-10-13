package com.fatal;

import com.fatal.dao.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Chapter12ApplicationTests {

    @Autowired
    private RedisDao dao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        dao.setKey("name", "fatal");
        dao.setKey("age", "11");
        log.info("【name】 = {}", dao.getValue("name"));
        log.info("【age】 = {}", dao.getValue("age"));
    }

    @Test
    public void fun1() {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        System.out.println(factory);
        System.out.println(factory instanceof LettuceConnectionFactory);      // true
    }

    @Test
    public void fun2() {
        System.out.println(redisTemplate.getValueSerializer());
        System.out.println(stringRedisTemplate.getValueSerializer());
    }

}
