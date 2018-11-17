package com.fatal;

import com.fatal.dao.ProductDao;
import com.fatal.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter254ApplicationTests {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsert() {
        Product product = new Product();
        product.setName("童年·在人间·我的大学");
        product.setOnlineTime(new Date());
        product.setBuyPrice(new BigDecimal("29.5"));
        product.setProvider("fatal");
        product.setCategory("book");
        product.setUpdateTime(new Date());
        productDao.save(product);
        System.out.println("new product id:"+product.getId());
    }

    @Test
    public void testUpdate(){
        Product product = new Product().setId(1l);
        product = productDao.findOne(Example.of(product)).orElse(null);

        if (product != null) {
            product.setName("偷影子的人");
            product.setBuyPrice(new BigDecimal("23.5"));
            product.setOnlineTime(new Date());
            productDao.save(product);
        }
    }

    @Test
    public void testDelete(){
        productDao.deleteById(1l);
    }

}
