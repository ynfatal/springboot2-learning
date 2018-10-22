package com.fatal.fanout.receiver;

import com.fatal.fanout.config.FanoutRabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收者
 * @author: Fatal
 * @date: 2018/10/22 0022 17:03
 */
@Slf4j
@Component
public class FanoutReceiver {

    @RabbitListener(queues = {FanoutRabbitConfig.FANOUT_A, FanoutRabbitConfig.FANOUT_B, FanoutRabbitConfig.FANOUT_C})
    public void receiveMessage(String messages) {
        log.info("【FanoutReceiver接收到消息】 -- [{}]", messages);
    }

}
