package com.fatal.topic.receiver;

import com.fatal.topic.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 接收者
 * @author: Fatal
 * @date: 2018/10/22 0022 15:09
 */
@Slf4j
@Component
//@RabbitListener(queues = TopicRabbitMQConfig.QUEUE_NAME)
public class TopicReceiver {

//    @RabbitHandler
    public void receiveMessage(User user) {
        log.info("【接收的User信息】--[{}]", user);
    }

}
