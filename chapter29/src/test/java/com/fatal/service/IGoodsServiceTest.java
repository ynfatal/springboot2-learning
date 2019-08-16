package com.fatal.service;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.dto.ShopCartGoodsDTO;
import com.fatal.entity.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fatal
 * @date 2019/8/14 0014 18:27
 */
public class IGoodsServiceTest extends Chapter29ApplicationTests {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 测试缓存
     */
    @Test
    public void getById() {
        Goods goods = goodsService.getById(1L);
        System.out.println(goods);
    }

    @Test
    public void getShopCartGoodsById() {
        ShopCartGoodsDTO dto = goodsService.getShopCartGoodsById(1L);
        System.out.println(dto);
    }

}