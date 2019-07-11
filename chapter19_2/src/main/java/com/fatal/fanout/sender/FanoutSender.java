package com.fatal.fanout.sender;

import com.fatal.fanout.config.FanoutRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发布者
 * @author: Fatal
 * @date: 2018/10/22 0022 17:02
 */
@Slf4j
@Component
public class FanoutSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public FanoutSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String message = "测试FanoutExchange实现发布与订阅";
        log.info("【FanoutSender发布消息】 -- [{}]", message);
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.FANOUT_EXCHANGE, "", message);
    }



}
