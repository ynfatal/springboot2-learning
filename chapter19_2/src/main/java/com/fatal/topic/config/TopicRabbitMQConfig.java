package com.fatal.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * @author: Fatal
 * @date: 2018/10/22 0022 14:55
 */
@Configuration
public class TopicRabbitMQConfig {

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static final String TOPIC_QUEUE = "topic_queue";

    private static final String BINDING_KEY = "fatal.#";

    @Bean
    public Queue topicQueue() {
        return new Queue(TOPIC_QUEUE, true);
    }

    /** Topic交换机 */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     *  binding组件
     *  `@Qualifier`: 当容器中出现同种类型的多个组件上，需要用`@Qualifier`来指定使用哪个
     *  将队列与交换机进行绑定并设置其`路由`
     *  `fatal.#`:这里的`#`表示匹配所有的意思
     */
    @Bean
    public Binding topicBinding(@Qualifier("topicQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(BINDING_KEY);
    }

    /**
     * 使用这种方式也可以一个消费者同时订阅多个queue
     * 这种方式与在消费者中用@RabbitListener、@RabbitListener+@RabbitHandler组合 两种指定队列的方式效果一致。
     * 注意：三种方式的消费者记得加 @Component。因为它必须是个组件
     */
    /*@Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(TopicRabbitMQConfig.TOPIC_QUEUE);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(TopicReceiver topicReceiver) {
        // 对接收者`Receiver`进行封装，指定接收消息de方法为`receiveMessage`
        return new MessageListenerAdapter(topicReceiver, "receiveMessage");
    }*/

}
