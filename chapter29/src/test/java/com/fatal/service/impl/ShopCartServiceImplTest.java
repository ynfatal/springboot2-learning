package com.fatal.service.impl;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartMainDTO;
import com.fatal.service.IShopCartService;
import com.fatal.utils.JsonUtil;
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

    private Long userId = 123456L;
    private Long goodsId = 111111L;
    private Long[] goodsIds;

    @Before
    public void before() {
        goodsIds = new Long[]{111111L, 222222L};
    }

    @Autowired
    private IShopCartService shopCartService;

    @Test
    public void increment() {
        shopCartService.increment(userId, goodsId, 1L);
    }

    @Test
    public void remove() {
        shopCartService.remove(userId, goodsId);
    }

    @Test
    public void shopCarts() {
        List<ShopCartDTO> shopCartDTOs = shopCartService.shopCarts(userId, Arrays.asList(1L, 2L, 4L, 5L, 6L));
        String json = JsonUtil.toJson(shopCartDTOs);
        System.out.println(json);
    }

    @Test
    public void delete() {
        shopCartService.remove(userId, goodsIds);
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
    
    @Test
    public void putMany() {
        for (int i = 200; i >= 0; i--) {
            shopCartService.increment(userId, (long) i, 1L);
        }
    }

}