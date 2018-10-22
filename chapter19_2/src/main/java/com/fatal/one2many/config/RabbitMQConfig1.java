package com.fatal.one2many.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2018/10/22 0022 11:15
 */
@Configuration
public class RabbitMQConfig1 {

    public static final String QUEUE_NAME = "fatal";

    /**
     * 配置一个队列`QUEUE_NAME`
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

}
