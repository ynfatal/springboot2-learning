package com.fatal;

import com.fatal.controller.FatalController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter23ApplicationTests {

    @Autowired
    private FatalController controller;

    /**
     * 测试构造方法注入组件不需要在构造方法上加注解`@Autowired`
     */
    @Test
    public void contextLoads() {
        System.out.println(controller.getHandler());
    }

}
