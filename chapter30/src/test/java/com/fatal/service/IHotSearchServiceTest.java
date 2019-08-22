package com.fatal.service;

import com.fatal.Chapter30ApplicationTests;
import com.fatal.common.constants.HotSearchConstant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Fatal
 * @date 2019/8/22 0022 10:29
 */
public class IHotSearchServiceTest extends Chapter30ApplicationTests {

    @Autowired
    private IHotSearchService hotSearchService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void init() {
        BoundZSetOperations<String, Serializable> zSetOperations = redisTemplate.boundZSetOps(HotSearchConstant.HOT_SEARCH);
        Random random = new Random();
        List<Long> ids = goodsService.getIds();
        ids.forEach(id -> {
            int score = random.nextInt(100000);
            zSetOperations.incrementScore(id, score);
        });
    }

    @Test
    public void incrementTest() {
        hotSearchService.increment(1164345124273057799L);
    }

    @Test
    public void hotSearchListTest() {
        List<Long> ids = hotSearchService.hotSearchList();
        ids.forEach(System.out::println);
    }

    @Test
    public void hotSearchWithScoreListTest() {
        Map<Serializable, Double> map = hotSearchService.hotSearchWithScoreList();
        map.entrySet().forEach(System.out::println);
    }
}