package com.fatal;

import com.fatal.entity.Product;
import com.fatal.hodler.CurrentUserHolder;
import com.fatal.serivce.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter251ApplicationTests {

    @Autowired
    private IProductService productService;

    /**
     * 测试用户没有添加权限
     * 执行结果：抛异常 java.lang.RuntimeException: operation not allow
     */
    @Test
    public void insert() {
        CurrentUserHolder.set("tom");
        productService.insert(new Product().setId(1l).setName("product"));
    }

    /**
     * 测试用户具有添加权限
     * 执行结果：正常删除
     */
    @Test
    public void delete() {
        CurrentUserHolder.set("admin");
        productService.delete(1l);
    }

}
