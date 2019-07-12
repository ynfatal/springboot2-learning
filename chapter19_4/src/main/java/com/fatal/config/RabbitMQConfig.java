package com.fatal.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2018/10/23 0023 11:23
 */
@Slf4j
@Configuration
public class RabbitMQConfig {

    /**
     * 延迟相关名称
     */
    private static final String DELAY_QUEUE_NAME = "delay_queue";
    public static final String DELAY_EXCHANGE_NAME = "delay_exchange";
    public static final String DELAY_ROUTING_KEY = "delay_book";

    /**
     * 死信相关名称
     */
    public static final String DEAD_LETTER_QUEUE_NAME = "dead_letter_queue";
    private static final String DEAD_LETTER_EXCHANGE_NAME = "dead_letter_exchange";
    // 一般使用原队列的路由键
    private static final String DEAD_LETTER_ROUTING_KEY = "delay_book";

    /**
     * 延迟队列中用来关联`死信交换机`的交换机键名，路由键键名和存活时间键名（这是参数值是`固定`的）
     */
    private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    private static final String X_MESSAGE_TTL = "x-message-ttl";

    // **************************   死信交换机相关配置   **************************

    /**
     * 死信队列
     */
    @Bean
    public Queue dlxQueue() {
        return new Queue(DEAD_LETTER_QUEUE_NAME);
    }

    /**
     * DLX（死信交换机）：dead letter发送到的exchange
     * @desc: 本质上是一个普通的交换机
     */
    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE_NAME);
    }

    /**
     * 绑定组件
     * @desc: 将`死信队列`、`死信交换机（DLX）`、`DEAD_LETTER_ROUTING_KEY`路由键 三者绑定
     */
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue())
                .to(dlxExchange())
                .with(DEAD_LETTER_ROUTING_KEY);
    }

    // ************************   死信交换机相关配置(end)   ************************

    // **************************   延迟交换机相关配置   **************************

    /**
     * 延迟队列
     * @desc: 与`死信交换机`绑定，并指定`死信`携带的路由键
     */
    @Bean
    public Queue delayQueue() {
        Map<String, Object> configs = new HashMap<>();
        // `x-dead-letter-exchange` 关联`DLX（死信交换机）`
        configs.put(X_DEAD_LETTER_EXCHANGE, DEAD_LETTER_EXCHANGE_NAME);
        // `x-dead-letter-routing-key` 声明了死信在转发时携带的 routing-key。
        configs.put(X_DEAD_LETTER_ROUTING_KEY, DEAD_LETTER_ROUTING_KEY);
        // `x-message-ttl`设置该队列中消息的存活时间（队列属性设置，队列中所有消息都有相同的过期时间）
//        configs.put(X_MESSAGE_TTL, 5 * 1000);
        /**
         * @param durable 声明持久化队列，则为true。（该队列在服务器重启之后继续存在）
         * @param exclusive 如果声明独占队列，则该队列将仅由声明者的连接使用
         * @param autoDelete 如果服务器不存在的时候应该将队列删除，则为true
         * @param arguments 用于声明队列的参数
         */
        return new Queue(DELAY_QUEUE_NAME, true, false, false, configs);
    }

    /**
     * 延迟交换机
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    /**
     * 绑定组件
     * @desc: 将`延迟队列`、`延迟交换机`、`DELAY_ROUTING_KEY`路由键 三者绑定
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue())
                .to(delayExchange())
                .with(DELAY_ROUTING_KEY);
    }

    // ************************   延迟交换机相关配置(end)   ************************

}
