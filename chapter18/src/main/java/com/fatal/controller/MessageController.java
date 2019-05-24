package com.fatal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * Message 控制器
 * @author: Fatal
 * @date: 2018/10/18 0018 16:07
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CountDownLatch latch;

    @PostMapping("/sendRedisMessage")
    public void sendRedisMessage(String message) throws InterruptedException {
        log.info("【控制器 MessageController】：生产者发布了<" + message + ">");
        stringRedisTemplate.convertAndSend("config", message);

        // 使当前线程等待直到闩锁计数为零，除非线程被中断。
        latch.await();

        // 结束程序
        System.exit(0);
    }

}
