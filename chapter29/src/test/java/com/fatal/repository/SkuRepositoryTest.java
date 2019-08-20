package com.fatal.repository;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.common.enums.StatusEnums;
import com.fatal.entity.Sku;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author Fatal
 * @date 2019/8/14 0014 17:33
 */
public class SkuRepositoryTest extends Chapter29ApplicationTests {

    @Autowired
    private SkuRepository skuRepository;

    @Test
    public void saveTest() {
        Sku sku = new Sku()
                .setGoodsId(444444L)
                .setShopId(123456L)
                .setShopName("Fatal Shop")
                .setGoodsName("限量款骑士领带")
                .setPrice(521000L)
                .setStock(1000)
                .setPicture("http://...pic...123.png")
                .setProperties("")
                .setMax(2)
                .setStatus(StatusEnums.NORMAL.getCode())
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now());
        skuRepository.save(sku);
    }

    @Test
    public void findByIdTest() {
        Sku sku = skuRepository.findById(1L)
                .orElseThrow(RuntimeException::new);
        System.out.println(sku);
    }

    @Test
    public void findByIdAndStatusIsNotTEst() {
        Sku sku = skuRepository.findByIdAndStatusIsNot(5L, StatusEnums.DELETED.getCode());
        System.out.println(sku);
    }

}