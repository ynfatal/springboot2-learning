package com.fatal.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Fatal
 * @date: 2019/7/6 0006 23:45
 */
@Configuration
public class DirectRabbitConfig {

    public static final String DIRECT_EXCHANGE = "direct_exchange";

    public static final String DIRECT_QUEUE = "direct_queue";

    public static final String ROUTING_KEY = "ynfatal";


    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directBinding(@Qualifier("directQueue") Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue)
                .to(directExchange)
                .with(ROUTING_KEY);
    }

}
