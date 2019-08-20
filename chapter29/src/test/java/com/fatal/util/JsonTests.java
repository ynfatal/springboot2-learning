package com.fatal.util;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.entity.Sku;
import com.fatal.common.enums.StatusEnums;
import com.fatal.common.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author Fatal
 * @date 2019/8/16 0016 9:40
 */
public class JsonTests extends Chapter29ApplicationTests {

    private Sku sku;

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
    }

    @Test
    public void fun() {
        String json = JsonUtil.toJson(this.sku);
        System.out.println(json);
        Sku sku = JsonUtil.fromJson(json, Sku.class);
        System.out.println(sku);
    }


}
