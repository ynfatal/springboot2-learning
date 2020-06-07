package com.fatal.redis;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.common.constants.ShopCartConstant;
import com.fatal.dto.RedisTestDTO;
import com.fatal.entity.Sku;
import com.fatal.common.enums.StatusEnums;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 执行完放可以输入 redis 命令 > object encoding [key] 来查看底层数据结构
 * @author Fatal
 * @date 2019/8/16 0016 9:44
 */
public class RedisTests extends Chapter29ApplicationTests {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    private Sku sku;

    private RedisTestDTO redisTestDTO;

    @Before
    public void before() {
        sku = new Sku()
                .setGoodsId(111111L)
                .setShopId(123457L)
                .setShopName("MiCai Shop")
                .setGoodsName("帆布鞋")
                .setPrice(15000L)
                .setStock(100)
                .setPicture("http://...pic...123.png")
                .setProperties("白色;40")
                .setMax(300)
                .setStatus(StatusEnums.NORMAL.getCode())
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());

        redisTestDTO = new RedisTestDTO()
                .setSkuId(Long.MAX_VALUE)
                .setCount(ShopCartConstant.MAX);
    }

    @Test
    public void zSetTest() {
        String key = "ZSET_TEST";
        ZSetOperations<String, Serializable> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add(key, redisTestDTO, 1);   // skiplist
        Set<Serializable> range = zSetOperations.range(key, 0, -1);
        assert range != null;
        range.forEach(System.out::println);
    }

    @Test
    public void hashTest() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("Cart", "用户id", sku);
        Sku result = (Sku) hashOperations.get("Cart", "用户id");
        System.out.println(result);
    }

    @Test
    public void stringTest() {
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("SKU", sku);
        Serializable sku = valueOperations.get("SKU");
        System.out.println(sku);
    }

    @Test
    public void listTest() {
        String key = "LIST_TEST";
        ListOperations<String, Serializable> listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, redisTestDTO);
        List<Serializable> range = listOperations.range(key, 0, -1);
        assert range != null;
        range.forEach(System.out::println);
    }

    @Test
    public void shopCartTest1() {
        ArrayList<Sku> list = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            list.add(sku);
        }
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("shopCart120", list);
    }

    @Test
    public void scanTest() {
        try {
            HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
            // 设置 count 选项来指定每次迭代返回元素的最大值，设置 match 指定需要匹配的 pattern
            Cursor<Map.Entry<Object, Object>> cursor = hashOperations.scan("SHOP_CART:999999",
                    ScanOptions.scanOptions().count(120).match("*").build());
            // 带顺序的 HashMap，同一个元素就算被返回多次也不影响。
            Map<Object, Object> linkedHashMap = new LinkedHashMap<>();
            while (cursor.hasNext()) {
                Map.Entry<Object, Object> entry = cursor.next();
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
            cursor.close();
            System.out.println(linkedHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
