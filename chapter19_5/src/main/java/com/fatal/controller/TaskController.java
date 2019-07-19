package com.fatal.controller;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: Fatal
 * @date: 2019/7/12 0012 8:56
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public void task() {
        User user = new User()
                .setUsername("fatal")
                .setPassword("123456")
                .setBirthday(new Date())
                .setId(1L);
        log.info("[Task 发送时间] - [{}]", LocalDateTime.now());
        rabbitTemplate.convertAndSend(RabbitMQConfig.TASK_EXCHANGE, RabbitMQConfig.TASK_ROUTING_KEY, user, message -> {
            /**
             * 将发送的消息设置为持久化
             */
            message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return message;
        });
    }

}
