package com.fatal.dao;

import com.fatal.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: Fatal
 * @date: 2018/11/15 0015 11:41
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductDaoTest {

    @Autowired
    private ProductDao dao;

    Product product;

    @Before
    public void before() {
        product = new Product().setName("童年·在人间·我的大学")
                .setCategory("书")
                .setProvider("fatal")
                .setBuyPrice(new BigDecimal(33.33))
                .setSellPrice(new BigDecimal(44.44))
                .setUpdateTime(new Date());
    }

    @Test
    public void save() {
        Product save = dao.save(product);
        System.out.println("【Product新增成功】 -- [Product = " +  save +"]");
    }


}