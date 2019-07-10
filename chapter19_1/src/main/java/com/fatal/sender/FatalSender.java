package com.fatal.sender;

import com.fatal.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 发送者
 * @author: Fatal
 * @date: 2018/10/22 0022 10:44
 */
@Slf4j
@Component
public class FatalSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public FatalSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String context = "现在时刻" + new Date();
        log.info("【FatalSender】发布de消息：[{}]", context);
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, context);
    }

}
