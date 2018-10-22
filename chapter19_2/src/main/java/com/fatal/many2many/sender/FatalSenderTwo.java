package com.fatal.many2many.sender;

import com.fatal.many2many.config.RabbitMQConfig3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送者
 * @author: Fatal
 * @date: 2018/10/22 0022 10:44
 */
@Slf4j
@Component
public class FatalSenderTwo {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String context = "我吃了两个苹果";
        log.info("【FatalSenderTwo】发布de消息：[{}]", context);
        rabbitTemplate.convertAndSend(RabbitMQConfig3.QUEUE_TWO_NAME, context);
    }

}