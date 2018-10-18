package com.fatal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/sendRedisMessage")
    public void sendRedisMessage(String message) {
        log.info("【控制器 MessageController】：生产者发布了<" + message + ">");
        stringRedisTemplate.convertAndSend("topic", message);
    }

}
