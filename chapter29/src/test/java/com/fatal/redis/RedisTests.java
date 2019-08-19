package com.fatal.redis;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.dto.RedisTestDTO;
import com.fatal.entity.Goods;
import com.fatal.enums.StatusEnums;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Fatal
 * @date 2019/8/16 0016 9:44
 */
public class RedisTests extends Chapter29ApplicationTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    private Goods goods;

    private RedisTestDTO redisTestDTO;

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

        redisTestDTO = new RedisTestDTO()
                .setGoodsId(Long.MAX_VALUE)
                .setCount(10000);
    }

    @Test
    public void zsetTest() {
        String key = "ZSET_TEST";
        ZSetOperations<String, Serializable> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(key, redisTestDTO, 1);   // skiplist
        Set<Serializable> range = zSetOperations.range(key, 0, -1);
        range.forEach(System.out::println);
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
        valueOperations.set("GOODS", goods);
        Serializable goods = valueOperations.get("GOODS");
        System.out.println(goods);
    }

    @Test
    public void listTest() {
        String key = "LIST_TEST";
        ListOperations<String, Serializable> listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, redisTestDTO);
        List<Serializable> range = listOperations.range(key, 0, -1);
        range.forEach(System.out::println);
    }

}
