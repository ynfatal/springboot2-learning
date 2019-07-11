package com.fatal.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FanoutRabbit 配置类
 * @desc: A、B、C三个队列绑定到Fanout交换机上
 * @author: Fatal
 * @date: 2018/10/22 0022 16:52
 */
@Configuration
public class FanoutRabbitConfig {

    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String FANOUT_A = "fanout.A";
    public static final String FANOUT_B = "fanout.B";
    public static final String FANOUT_C = "fanout.C";

    @Bean
    public Queue queueA() {
        return new Queue(FANOUT_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(FANOUT_B);
    }

    @Bean
    public Queue queueC() {
        return new Queue(FANOUT_C);
    }

    /** Fanout交换机 */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBindingA(Queue queueA, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(queueA)
                .to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingB(Queue queueB, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(queueB)
                .to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingC(Queue queueC, FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(queueC)
                .to(fanoutExchange);
    }

}
