package com.fatal.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2018/10/22 0022 10:41
 */
@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "fatal";

    /**
     * 配置一个队列`QUEUE_NAME`
     */
    @Bean
    public Queue queue() {
        /*
          在 RabbitMQ 服务器上创建一个 `QUEUE_NAME` 队列
          由于没有指定交换机，所以会与`RabbitMQ 服务器`的默认交换机(AMQP default)绑定，
          路由键为该队列的名称
         */
        return new Queue(QUEUE_NAME);
    }

}
