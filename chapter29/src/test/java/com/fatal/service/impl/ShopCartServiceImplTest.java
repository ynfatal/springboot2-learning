package com.fatal.service.impl;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.common.utils.JsonUtil;
import com.fatal.dto.ShopCartDTO;
import com.fatal.service.IShopCartService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Fatal
 * @date 2019/8/15 0015 8:54
 */
public class ShopCartServiceImplTest extends Chapter29ApplicationTests {

    private Long userId = 999999L;

    @Autowired
    private IShopCartService shopCartService;

    @Test
    public void incrementOne() {
        shopCartService.increment(userId, 1L, 101L);
    }

    @Test
    public void increment() {
        shopCartService.increment(userId,2L, 100L);
        shopCartService.increment(userId,4L, 100L);
        shopCartService.increment(userId,9L, 100L);
        shopCartService.increment(userId,3L, 100L);
        shopCartService.increment(userId,8L, 100L);
        shopCartService.increment(userId,12L, 100L);
        shopCartService.increment(userId,1L, 100L);
        shopCartService.increment(userId,6L, 100L);
        shopCartService.increment(userId,10L, 100L);
        shopCartService.increment(userId,11L, 100L);
        shopCartService.increment(userId,7L, 100L);
    }

    @Test
    public void removeOne() {
        shopCartService.removeOne(userId, 7L);
    }

    @Test
    public void shopCarts() {
        List<ShopCartDTO> shopCartDTOs = shopCartService.shopCarts(userId, 3);
        String json = JsonUtil.toJson(shopCartDTOs);
        System.out.println(json);
    }

    @Test
    public void delete() {
        Long[] skuIds = new Long[]{7L, 10L, 2L};
        shopCartService.remove(userId, skuIds);
    }

    @Test
    public void clear() {
        shopCartService.clear(userId);
    }

    @Test
    public void into() {
        Integer totalPage = shopCartService.into(userId);
        System.out.println(totalPage);
    }

    @Test
    public void shopCartGrouping() {
        List<Long> list = shopCartService.shopCartGrouping(userId);
        System.out.println(list);
    }

}