package com.fatal.util;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.entity.Goods;
import com.fatal.enums.StatusEnums;
import com.fatal.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author Fatal
 * @date 2019/8/16 0016 9:40
 */
public class JsonTests extends Chapter29ApplicationTests {

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

    @Test
    public void fun() {
        String json = JsonUtil.toJson(goods);
        System.out.println(json);
        Goods goods = JsonUtil.fromJson(json, Goods.class);
        System.out.println(goods);
    }


}
