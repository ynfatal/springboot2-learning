package com.fatal.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fatal.Chapter30ApplicationTests;
import com.fatal.common.enums.StatusEnums;
import com.fatal.entity.Goods;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/8/19 0019 20:12
 */
public class GoodsMapperTest extends Chapter30ApplicationTests {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    public void selectByIdTest() {
        Goods goods = goodsMapper.selectById(1L);
        System.out.println(goods);
    }

    @Test
    public void selectListTest() {
        List<Goods> list = goodsMapper.selectList(
                new LambdaQueryWrapper<Goods>()
                        .eq(Goods::getShopName, "Fatal Shop")
        );
        print(list);
    }

    @Test
    public void insertTest() {
        goodsMapper.insert(new Goods()
                .setName("巧克力冰淇淋")
                .setShopId(123458L)
                .setShopName("MiCai Shop")
                .setPicture("http://...pic...123.png")
                .setPrice(5000L)
                .setContent("买二送一")
                .setCreateTime(LocalDateTime.now())
                .setUpdateTime(LocalDateTime.now())
                .setStock(100)
                .setStatus(StatusEnums.NORMAL.getCode()));
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

}