package com.fatal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j  // 它是一个的用于日志管理的注解
public class Chapter3ApplicationTests {

    @Test
    public void contextLoads() {
        log.info("Hello World");
    }

    /**
     * 低于默认级别的不会输出
     */
    @Test
    public void testDefault() {
        log.trace("trace...");
        log.debug("debug...");
        log.info("info...");    // 默认级别，为root级别
        log.warn("warn...");
        log.error("error...");
    }

    /**
     * 在日志中输出变量，@Slf4j 提供了更加方便的写法
     */
    @Test
    public void printVariable() {
        String username = "fatal";
        String password = "123456";

        //你可能会这样写，这样子拼接的时候尤其是在变量特别多的时候，你可能会特别的头疼
        log.info("username: " + username + ",password: " + password);
        //Slf4j提供了一种方便的写法
        log.info("username: {} ,password: {} ",username,password);
    }

}
