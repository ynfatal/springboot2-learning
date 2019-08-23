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
                .setGoodsId(151312L)
                .setShopId(123457L)
                .setShopName("MiCai Shop")
                .setGoodsName("胸针")
                .setPrice(191000L)
                .setStock(1000)
                .setPicture("http://...pic...123.png")
                .setProperties("骑士红")
                .setMax(10)
                .setStatus(StatusEnums.PROHIBIT.getCode())
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