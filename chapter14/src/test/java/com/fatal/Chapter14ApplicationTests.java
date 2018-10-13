package com.fatal;

import com.fatal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter14ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    /**
     * 测试默认模板
     */
    @Test
    public void testString() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "name";
        ops.set(key, "米琪");
        String name = ops.get(key);
        log.info("测试默认模板【字符串缓存结果】 = {}", name);
    }

    /**
     * 测试自定义模板
     */
    @Test
    public void testUser() {
        ValueOperations<String, Serializable> ops = serializableRedisTemplate.opsForValue();
        User user = new User(1l, "米彩", "18");
        String key = "fatal:user";
        ops.set(key, user);
        User result = (User)ops.get(key);
        log.info("测试自定义模板【对象缓存结果】 = {}", result);
    }

}
