package com.fatal.service;

import com.fatal.Chapter30ApplicationTests;
import com.fatal.dto.GoodsDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author Fatal
 * @date 2019/8/22 0022 10:12
 */
public class IGoodsServiceTest extends Chapter30ApplicationTests {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 查看详情，商品热搜度 +1
     */
    @Test
    public void getDetails() {
        GoodsDTO details = goodsService.getDetails(1164345124273057799L);
        System.out.println(details);
    }
}