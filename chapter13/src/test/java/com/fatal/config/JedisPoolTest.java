package com.fatal.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: Fatal
 * @date: 2018/10/13 0013 14:08
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JedisPoolTest {

    @Autowired
    private JedisPool pool;

    private Jedis jedis;

    @Before
    public void before () {
        this.jedis = pool.getResource();
    }

    @Test
    public void fun() {
        jedis.set("name", "米彩");
        jedis.set("age", "18");
        log.info("【name】 = {}", jedis.get("name"));
        log.info("【age】 = {}", jedis.get("age"));
    }

}