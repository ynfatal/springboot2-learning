package com.fatal.receiver;

import com.fatal.config.RabbitMQConfig;
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
@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
public class FatalReceiver {

    @RabbitHandler
    public void process(String context) {
        log.info("【FatalReceiver】处理了：[{}]", context);
    }

}
