package com.fatal.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
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
     * 自定义RabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        /**
         *  仅适用于存在回调函数ReturnCallback的情况，
         *  如果发送方设置了mandatory 模式,则会先调用basic.return方法.
         */
        rabbitTemplate.setMandatory(true);
        /** 设置确认回调函数 */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info((ack?"消息发送成功":"消息发送失败") + "：time({}),correlationData({}), ack({}),cause({})", LocalDateTime.now(), connectionFactory, ack, cause));
        /** 设置返回回调函数 */
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("消息丢失：time({}),message({}), replyCode({}), replyText({}), exchange({}), routingKey({})",
                        LocalDateTime.now(), message, replyCode, replyText, exchange, routingKey));
        return rabbitTemplate;
    }

    /*
     * 延迟队列名称
     */
    public static final String REGISTER_DELAY_QUEUE_NAME = "dev.book.register.delay.queue";
    /**
     * 普通交换机de名称
     */
    public static final String REGISTER_DELAY_EXCHANGE_NAME = "dev.book.register.delay.exchange";
    /**
     * 通往延迟队列的路由键`DELAY_ROUTING_KEY`
     */
    public static final String DELAY_ROUTING_KEY = "";


    /**
     * 普通队列名称
     * @Desc: 该队列与`死信交换机`绑定，当死信交换机接受到消息后，会将消息放到此队列中
     */
    public static final String REGISTER_QUEUE_NAME = "dev.book.register.queue";
    /**
     * 死信交换机de名称
     */
    public static final String REGISTER_EXCHANGE_NAME = "dev.book.register.exchange";
    /**
     * 通往普通队列的路由键`ROUTING_KEY`
     * @Desc: 因为延迟队列在这里指定的`死信交换机`是`Topic交换机`，所以`路由键`必不可少！！！
     */
    public static final String ROUTING_KEY = "all";


    /**
     * 延迟队列中用来关联`死信交换机`的交换机键名，路由键键名和存活时间键名（这是名字是固定的）
     */
    private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    private static final String X_MESSAGE_TTL = "x-message-ttl";


    /**
     * 延迟队列
     * @Desc: 在队列中指定与某个`死信交换机`关联，并指定`死信`携带的路由键
     */
    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = new HashMap<>();
        // `x-dead-letter-exchange` 关联`DLX（死信交换机）`
        params.put(X_DEAD_LETTER_EXCHANGE, REGISTER_EXCHANGE_NAME);
        // `x-dead-letter-routing-key` 声明了死信在转发时携带的 routing-key。
        params.put(X_DEAD_LETTER_ROUTING_KEY, ROUTING_KEY);
        /** `x-message-ttl`指定该队列为延迟队列（给队列设置存活时间） */
//        params.put(X_MESSAGE_TTL, 5 * 1000);
        return new Queue(REGISTER_DELAY_QUEUE_NAME, true, false, false, params);
    }

    /**
     * 普通交换机
     * @Desc: 与延迟队列进行绑定
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(REGISTER_DELAY_EXCHANGE_NAME);
    }

    /**
     * `delayBinding`组件
     * @Desc: 将延迟队列、普通交换机、`DELAY_ROUTING_KEY`路由键 三者绑定
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayProcessQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }


    /**
     * 普通队列
     * @Desc: 与`死信交换机`绑定的队列，在`死信交换机`接收到消息后，会发送给该队列
     */
    @Bean
    public Queue registerBookQueue() {
        return new Queue(REGISTER_QUEUE_NAME, true);
    }

    /**
     * DLX（死信交换机）：dead letter发送到的exchange
     * @Desc: 本质上是一个普通的交换机，当延迟队列中指定该交换机为`死信交换机`后，它就相对那个延迟队列
     * 就是死信交换机。简单来说，死信交换机就是多一个功能，可以接受与它关联的延迟队列的消息
     */
    @Bean
    public TopicExchange registerBookTopicExchange() {
        return new TopicExchange(REGISTER_EXCHANGE_NAME);
    }

    /**
     * `dlxBinding`组件
     * @Desc: 将普通队列、`死信交换机（DLX）`、`ROUTING_KEY`路由键 三者绑定
     */
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(registerBookQueue()).to(registerBookTopicExchange()).with(ROUTING_KEY);
    }

}
