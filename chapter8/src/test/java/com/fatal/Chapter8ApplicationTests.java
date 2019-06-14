package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter8ApplicationTests {

    @Autowired
    private MybatisProperties mybatisProperties;

    @Test
    public void contextLoads() {
        System.out.println("!!!");
    }

}
