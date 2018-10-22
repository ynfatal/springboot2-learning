package com.fatal.one2many.receiver;

import com.fatal.one2many.config.RabbitMQConfig1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收者
 * @author: Fatal
 * @date: 2018/10/22 0022 10:45
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMQConfig1.QUEUE_NAME)
public class ReceiverTwo {

    @RabbitHandler
    public void process(String context) {
        log.info("【ReceiverTwo】处理了：[{}]", context);
    }

}
