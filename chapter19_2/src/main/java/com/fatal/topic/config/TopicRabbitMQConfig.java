package com.fatal.topic.config;

import com.fatal.topic.receiver.TopicReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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

    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange_name";

    public static final String QUEUE_NAME = "queue_name";

    @Bean
    Queue topicQueue() {
        return new Queue(QUEUE_NAME, false);
    }

    /** Topic交换机 */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    /**
     *  binding组件
     *  `@Qualifier`: 当容器中出现同种类型的多个组件上，需要用`@Qualifier`来指定使用哪个
     *  将队列与交换机进行绑定并设置其`路由`
     *  `fatal.#`:这里的`#`表示匹配所有的意思
     */
    @Bean
    Binding binding(@Qualifier("topicQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("fatal.#");
    }

    /**
     * 使用这种方式也可以一个接收者同时订阅多个queue
     * 这种方式与在接收者中用@RabbitListener、@RabbitHandler组合指定队列效果一致。
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(TopicRabbitMQConfig.QUEUE_NAME);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(TopicReceiver topicReceiver) {
        /** 对接收者`Receiver`进行封装，指定接受消息de方法为`receiveMessage` */
        return new MessageListenerAdapter(topicReceiver, "receiveMessage");
    }

}
