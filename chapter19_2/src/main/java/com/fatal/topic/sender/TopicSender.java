package com.fatal.topic.sender;

import com.fatal.topic.config.TopicRabbitMQConfig;
import com.fatal.topic.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发布者
 * @author: Fatal
 * @date: 2018/10/22 0022 15:52
 */
@Slf4j
@Component
public class TopicSender {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public TopicSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        User user = new User(1L, "fatal", "123");
        log.info("【Sender发布的User信息】：[{}]", user);
        rabbitTemplate.convertAndSend(TopicRabbitMQConfig.TOPIC_EXCHANGE, "fatal.user" ,user);
    }
}
