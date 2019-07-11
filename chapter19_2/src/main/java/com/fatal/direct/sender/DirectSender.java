package com.fatal.direct.sender;

import com.fatal.direct.config.DirectRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Fatal
 * @date: 2019/7/7 0007 0:03
 */
@Component
@Slf4j
public class DirectSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public DirectSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String message = "测试 DirectExchange";
        log.info("【DirectSender发布消息】 -- [{}]", message);
        rabbitTemplate.convertAndSend(DirectRabbitConfig.DIRECT_EXCHANGE,
                DirectRabbitConfig.ROUTING_KEY, message);
    }

}
