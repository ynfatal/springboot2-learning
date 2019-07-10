package com.fatal.receiver;

import com.fatal.config.RabbitMQConfig;
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
public class FatalReceiver {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void process(String context) {
        log.info("【FatalReceiver】处理了：[{}]", context);
        // 模拟消费者业务异常，观察自动 ack 的弊端。
        // 当消费者出现异常导致消费失败，这会导致消费者循环消费。
//        int i = 1/0;
    }

}
