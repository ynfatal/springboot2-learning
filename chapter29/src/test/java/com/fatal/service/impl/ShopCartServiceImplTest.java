package com.fatal.service.impl;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartMainDTO;
import com.fatal.service.IShopCartService;
import com.fatal.common.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/8/15 0015 8:54
 */
public class ShopCartServiceImplTest extends Chapter29ApplicationTests {

    private Long userId = 999999L;
    private Long skuId = 1L;
    private Long[] skuIds;

    @Before
    public void before() {
        skuIds = new Long[]{1L, 2L};
    }

    @Autowired
    private IShopCartService shopCartService;

    @Test
    public void increment() {
        shopCartService.increment(userId,2L, 100L);
        shopCartService.increment(userId,4L, 100L);
        shopCartService.increment(userId,3L, 100L);
        shopCartService.increment(userId,1L, 100L);
        shopCartService.increment(userId,6L, 100L);
        shopCartService.increment(userId,7L, 100L);
    }

    @Test
    public void removeOne() {
        shopCartService.removeOne(userId, skuId);
    }

    @Test
    public void shopCarts() {
        List<ShopCartDTO> shopCartDTOs = shopCartService.shopCarts(userId, Arrays.asList(7L, 2L, 1L, 4L, 3L, 6L));
        String json = JsonUtil.toJson(shopCartDTOs);
        System.out.println(json);
    }

    @Test
    public void delete() {
        shopCartService.remove(userId, skuIds);
    }

    @Test
    public void clear() {
        shopCartService.clear(userId);
    }

    @Test
    public void into() {
        List<ShopCartMainDTO> list = shopCartService.into(userId);
        System.out.println(JsonUtil.toJson(list));
    }

}