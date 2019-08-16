package com.fatal.repository;

import com.fatal.Chapter29ApplicationTests;
import com.fatal.entity.Goods;
import com.fatal.enums.StatusEnums;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author Fatal
 * @date 2019/8/14 0014 17:33
 */
public class GoodsRepositoryTest extends Chapter29ApplicationTests {

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void saveTest() {
        Goods goods = new Goods()
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
        goodsRepository.save(goods);
    }

    @Test
    public void findByIdTest() {
        Goods goods = goodsRepository.findById(1L)
                .orElseThrow(RuntimeException::new);
        System.out.println(goods);
    }

}