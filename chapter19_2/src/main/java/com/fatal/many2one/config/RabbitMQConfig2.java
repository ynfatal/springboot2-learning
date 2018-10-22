package com.fatal.many2one.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2018/10/22 0022 11:15
 */
@Configuration
public class RabbitMQConfig2 {

    public static final String QUEUE_ONE_NAME = "queue_one_name";
    public static final String QUEUE_TWO_NAME = "queue_two_name";

    /**
     * 配置队列`QUEUE_ONE_NAME`
     */
    @Bean
    public Queue queueOne() {
        return new Queue(QUEUE_ONE_NAME);
    }

    /**
     * 配置队列`QUEUE_TWO_NAME`
     */
    @Bean
    public Queue queueTwo() {
        return new Queue(QUEUE_TWO_NAME);
    }

}
