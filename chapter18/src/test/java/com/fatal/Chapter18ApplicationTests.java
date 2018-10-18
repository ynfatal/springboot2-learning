package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter18ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("fatal", "fatal");
        String value = ops.get("fatal");
        System.out.println(value);
    }

    @Test
    public void fun() {
        System.out.println(stringRedisTemplate.getConnectionFactory());
        /**
         * org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory@5875de6a
         */
    }

}
