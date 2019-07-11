package com.fatal.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2018/10/20 0020 9:32
 */
@Slf4j
@Configuration
public class RabbitMQConfig {

    public static final String CONFIRM_QUEUE = "confirm_queue";
    public static final String CONFIRM_EXCHANGE = "confirm_exchange";
    public static final String ROUTING_KEY = "book";

    /** 日志模板 */
    private final String EXIST = "RabbitMQ Block 的 Exchange({})接收到消息";
    private final String NOT_EXIST = "RabbitMQ Block 不存在此交换机 ({})";

    /**
     * 自定义 RabbitTemplate，CachingConnectionFactory 是必须的
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置 mandatory 为 true，否则消息路由失败不会回调 ReturnCallback
        rabbitTemplate.setMandatory(true);
        // 设置确认回调函数
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info((ack ? EXIST : NOT_EXIST) + "：time({}), ack({}),cause({})",
                        correlationData.getId(), LocalDateTime.now(), ack, cause));
        // 设置返回回调函数
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("消息路由失败：time({}),message({}), replyCode({}), replyText({}), exchange({}), routingKey({})",
                        LocalDateTime.now(), message, replyCode, replyText, exchange, routingKey));
        return rabbitTemplate;
    }

    @Bean
    public Queue confirmBookQueue() {
        return new Queue(CONFIRM_QUEUE);
    }

    @Bean
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange confirmExchange) {
        return BindingBuilder.bind(queue)
                .to(confirmExchange)
                .with(ROUTING_KEY);
    }

}
