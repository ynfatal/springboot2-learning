package com.fatal.service;

import com.fatal.anno.AdminOnly;
import com.fatal.anno.NeedSecured;
import com.fatal.anno.NeedSecuredClass;
import com.fatal.entity.Product;
import org.springframework.stereotype.Component;

/**
 * @author: Fatal
 * @date: 2018/11/10 0010 14:31
 */
@Component
@NeedSecured
@NeedSecuredClass
public class ProductService {

    @AdminOnly
    public void execute() {
        System.out.println("product method execute");
    }

    public void exDemo() throws IllegalAccessException {
        System.out.println("product method ex demo");
        throw new IllegalAccessException("this is a exception");
    }

    public void findById(Long id) {
        System.out.println("product method find by id");
    }

    public void findByTwoArgs(Long id,String name) {
        System.out.println("product method find by id and name");
    }

    /**
     * 测试`匹配注解`@args
     */
    public void annoArgs(Product product) {
        System.out.println("annoArgs method's parameter with @NeedSecured");
    }
}
