package com.fatal.many2many.receiver;

import com.fatal.many2many.config.RabbitMQConfig3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收者
 * @author: Fatal
 * @date: 2018/10/22 0022 10:45
 */
@Slf4j
@Component
public class FatalReceiverTwo {

    @RabbitListener(queues = RabbitMQConfig3.QUEUE_ONE_NAME)
    public void receiver1(String context) {
        log.info("【FatalReceiverTwo的接收器receiver1】处理了：[{}]", context);
    }

    @RabbitListener(queues = RabbitMQConfig3.QUEUE_TWO_NAME)
    public void receiver2(String context) {
        log.info("【FatalReceiverTwo的接收器receiver2】处理了：[{}]", context);
    }

}
