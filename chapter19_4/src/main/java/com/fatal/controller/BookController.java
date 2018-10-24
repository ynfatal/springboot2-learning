package com.fatal.controller;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Book 控制器
 * @author: Fatal
 * @date: 2018/10/23 0023 12:37
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private final RabbitTemplate rabbitTemplate;

    private final String NO = "no";

    /**
     * 使用构造方法注入
     */
    @Autowired
    public BookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * exchange, routingKey 都正确，能匹配到队列
     * 结果：confirm 回调，ack 为 true
     */
    @GetMapping("/1")
    public void defaultMessage1() {
        Book book = new Book(1l, "六月与便士");
        // 添加延迟队列
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.REGISTER_DELAY_EXCHANGE_NAME,
                RabbitMQConfig.DELAY_ROUTING_KEY, book, message -> {
                    // TODO 第一句是可要可不要,根据自己需要自行处理
                    message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Book.class.getName());
                    /**
                     *  如果你在`延迟队列`中配置了params.put("x-message-ttl", 5 * 1000);那么下面
                     *  这句就可以省略，要么定义延迟队列，要定义延迟消息。二选一即可
                     */
                    message.getMessageProperties().setExpiration(String.valueOf(5 * 1000));
                    return message;
                });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
    }

    /**
     * exchange 错误, routingKey 正确，交换机接收不到消息
     * 结果：confirm 回调，ack 为 false，reply-code=404, reply-text=NOT_FOUND
     */
    @GetMapping("/2")
    public void defaultMessage2() {
        Book book = new Book(1l, "六月与便士");
        // 添加延迟队列
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.REGISTER_DELAY_EXCHANGE_NAME + NO,
                RabbitMQConfig.DELAY_ROUTING_KEY, book, message -> {
                    /** 配置消息的TTL */
                    message.getMessageProperties().setExpiration(String.valueOf(5 * 1000));
                    return message;
                });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
    }

    /**
     * exchange 正确, routingKey 错误，不能匹配到队列
     * 结果：confirm 回调，ack 为 true
     *      return 回调，replyCode(312), replyText(NO_ROUTE)（没有路由，无路线）
     */
    @GetMapping("/3")
    public void defaultMessage3() {
        Book book = new Book(1l, "六月与便士");
        // 添加延迟队列
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.REGISTER_DELAY_EXCHANGE_NAME,
                RabbitMQConfig.DELAY_ROUTING_KEY + NO, book, message -> {
                    /** 配置消息的TTL */
                    message.getMessageProperties().setExpiration(String.valueOf(5 * 1000));
                    return message;
                });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
    }

    /**
     * exchange 错误, routingKey 错误，交换机接收不到消息
     * 结果：confirm 回调，ack 为 false，reply-code=404, reply-text=NOT_FOUND
     */
    @GetMapping("/4")
    public void defaultMessage4() {
        Book book = new Book(1l, "六月与便士");
        // 添加延迟队列
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.REGISTER_DELAY_EXCHANGE_NAME + NO,
                RabbitMQConfig.DELAY_ROUTING_KEY + NO, book, message -> {
                    /** 配置消息的TTL */
                    message.getMessageProperties().setExpiration(String.valueOf(5 * 1000));
                    return message;
                });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
    }

}
