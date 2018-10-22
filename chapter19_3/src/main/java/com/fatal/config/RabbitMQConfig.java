package com.fatal.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2018/10/20 0020 9:32
 */
@Configuration
public class RabbitMQConfig {

    /** 定义两个队列名常量 */
    public static final String DEFAULT_BOOK_QUEUE = "default_book_queue";
    public static final String MANUAL_BOOK_QUEUE = "manual_book_queue";

    /**
     * 配置两个队列
     * public Queue(String name, boolean durable)
     * name:Queue的名字
     * durable:是否需要持久化处理
     */
    @Bean
    public Queue defaultBookQueue() {
        return new Queue(DEFAULT_BOOK_QUEUE, true);
    }

    @Bean
    public Queue manualBookQueue() {
        return new Queue(MANUAL_BOOK_QUEUE, true);
    }

}
