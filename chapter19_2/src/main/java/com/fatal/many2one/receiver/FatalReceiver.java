package com.fatal.many2one.receiver;

import com.fatal.many2one.config.RabbitMQConfig2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收者`FatalReceiver`
 * @author: Fatal
 * @date: 2018/10/22 0022 10:45
 */
@Slf4j
@Component
public class FatalReceiver {

    @RabbitListener(queues = RabbitMQConfig2.QUEUE_ONE_NAME)
    public void receiverOne(String context) {
        log.info("【FatalReceiver的接收器receiverOne】处理了：[{}]", context);
    }

    @RabbitListener(queues = RabbitMQConfig2.QUEUE_TWO_NAME)
    public void receiverTwo(String context) {
        log.info("【FatalReceiver的接收器receiverTwo】处理了：[{}]", context);
    }

}
