package com.fatal.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2019/7/12 0012 8:51
 */
@Configuration
public class RabbitMQConfig {

    /**
     * 任务相关配置
     */
    public static final String TASK_QUEUE = "task_queue";
    public static final String TASK_EXCHANGE = "task_exchange";
    public static final String TASK_ROUTING_KEY = "task";

    /**
     * 异常处理相关配置
     */
    public static final String EXCEPTION_HANDLING_QUEUE_NAME = "exception_handling_queue_name";
    private static final String EXCEPTION_HANDLING_EXCHANGE_NAME = "exception_handling_exchange_name";
    // 一般使用原队列的路由键
    private static final String EXCEPTION_HANDLING_ROUTING_KEY = "task";

    /**
     * 延迟队列中用来关联`死信交换机`的交换机键名，路由键键名（这是参数值是`固定`的）
     */
    private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    // ************************   JSON 序列化与反序列化   ***********************

    /**
     * RabbitTemplate 的消息转换器设置为 Jackson2JsonMessageConverter（默认转换器是 SimpleMessageConverter）
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

    /**
     * 在 SpringBoot 整合 RabbitMQ 的组件 SimpleRabbitListenerContainerFactory
     * 默认配置的基础上，将 MessageConverter（默认是 SimpleMessageConverter，该转换器基于 Jdk 序列化）
     * 设置为 Jackson2JsonMessageConverter（基于 Json 序列化，性能更好）
     * 下面的组件是从源码中找的，我们在这个组件的基础上进行修改的话，可以保留配置的属性。要是写个新的，很多
     * 配置就没意义了，比如，你在外面设置的手动ack没有了。它用的还是默认的 no ack。
     * @param configurer
     * @param connectionFactory
     * @return
     */
    @Bean(name = "rabbitListenerContainerFactory")
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = "type", havingValue = "simple",
            matchIfMissing = true)
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    // **********************   JSON 序列化与反序列化 end   *********************

    // ************************   异常处理交换机相关配置   ***********************

    /**
     * 异常处理队列
     */
    @Bean
    public Queue exceptionHandlerQueue() {
        return new Queue(EXCEPTION_HANDLING_QUEUE_NAME);
    }

    /**
     * 异常处理交换机
     */
    @Bean
    public DirectExchange exceptionHandlerExchange() {
        return new DirectExchange(EXCEPTION_HANDLING_EXCHANGE_NAME);
    }

    /**
     * 绑定组件
     * @desc: 将`死信队列`、`死信交换机（DLX）`、`DEAD_LETTER_ROUTING_KEY`路由键 三者绑定
     */
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(exceptionHandlerQueue())
                .to(exceptionHandlerExchange())
                .with(EXCEPTION_HANDLING_ROUTING_KEY);
    }

    // **********************   异常处理交换机相关配置(end)   **********************

    // **************************   Task交换机相关配置   **************************

    /**
     * 任务队列
     * @desc: 与`死信交换机`绑定，并指定`死信`携带的路由键，不需要设置消息的过期时间。
     *      消息只要是被 basicReject(requeue:true)、basicNack(requeue:true) 回来的，
     *      都会被转移到死信交换机那边。
     */
    @Bean
    public Queue taskQueue() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(X_DEAD_LETTER_EXCHANGE, EXCEPTION_HANDLING_EXCHANGE_NAME);
        configs.put(X_DEAD_LETTER_ROUTING_KEY, EXCEPTION_HANDLING_ROUTING_KEY);
        return new Queue(TASK_QUEUE, true, false, false, configs);
    }

    /**
     * 任务交换机
     */
    @Bean
    public DirectExchange taskExchange() {
        return new DirectExchange(TASK_EXCHANGE);
    }

    /**
     * 绑定组件
     */
    @Bean
    public Binding taskBinding() {
        return BindingBuilder.bind(taskQueue())
                .to(taskExchange())
                .with(TASK_ROUTING_KEY);
    }

    // ************************   Task交换机相关配置(end)   ************************

}
