package com.fatal.redis;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.entity.Goods;
import com.fatal.enums.StatusEnums;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Fatal
 * @date 2019/8/16 0016 9:44
 */
public class RedisTests extends Chapter29ApplicationTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    private Goods goods;

    @Before
    public void before() {
        goods = new Goods()
                .setShopId(123457L)
                .setShopName("MiCai Shop")
                .setName("菠萝啤")
                .setPrice(15000L)
                .setStock(100)
                .setPicture("http://...pic...123.png")
                .setContent("爽快...")
                .setStatus(StatusEnums.NORMAL.getCode())
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());
    }

    /**
     * 保存一个对象记录作为value
     */
    @Test
    public void save100Test() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        for (int i = 0; i < 100; i++) {
        }
    }

    @Test
    public void hashTest() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("Cart", "用户id", goods);
        Goods result = (Goods) hashOperations.get("Cart", "用户id");
        System.out.println(result);
    }

    @Test
    public void stringTest() {
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("GOODS", goods);
//        Serializable goods = valueOperations.get("GOODS");
//        System.out.println(goods);
        valueOperations.set("TEST", "qwertyuiopqwertyu");
    }

}
