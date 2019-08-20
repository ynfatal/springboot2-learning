package com.fatal.service;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.dto.ShopCartSkuDTO;
import com.fatal.entity.Sku;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fatal
 * @date 2019/8/14 0014 18:27
 */
public class ISkuServiceTest extends Chapter29ApplicationTests {

    @Autowired
    private ISkuService skuService;

    /**
     * 测试缓存
     */
    @Test
    public void getById() {
        Sku sku = skuService.getById(1L);
        System.out.println(sku);
    }

    @Test
    public void getShopCartSkuById() {
        ShopCartSkuDTO dto = skuService.getShopCartSkuById(1L);
        System.out.println(dto);
    }

}