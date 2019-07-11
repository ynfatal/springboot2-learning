package com.fatal.controller;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Book 控制器
 * @author: Fatal
 * @date: 2018/10/20 0020 9:46
 */
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    private RabbitTemplate rabbitTemplate;

    /**
     * 回调的相关数据（我拿来放交换机名称用了。。。）
     */
    private CorrelationData correlationData = new CorrelationData();

    @Autowired
    public BookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        correlationData.setId(RabbitMQConfig.CONFIRM_EXCHANGE);
    }

    private static String NO = "no";

    /**
     * 交换机和路由键都正确
     */
    @GetMapping("/1")
    public void send1() {
        Book book = new Book().setId("1").setName("米彩");
        log.info("[发送时间] - [{}]", LocalDateTime.now());
        // 发送到confirmExchange交换机
        rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE, RabbitMQConfig.ROUTING_KEY, book, correlationData);
        // 发送到默认交换机（默认交换机隐式的绑定到每个队列，路由键等于队列的名称）
        rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_QUEUE, book, correlationData);
    }

    /**
     * 交换机正确，路由键错误
     */
    @GetMapping("/2")
    public void send2() {
        Book book = new Book().setId("1").setName("米彩");
        log.info("[发送时间] - [{}]", LocalDateTime.now());
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE,RabbitMQConfig.ROUTING_KEY + NO, book, correlationData);
    }

    /**
     * 交换机错误（不管路由键是否正确）
     */
    @GetMapping("/3")
    public void send3() {
        Book book = new Book().setId("1").setName("米彩");
        log.info("[发送时间] - [{}]", LocalDateTime.now());
//        this.rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE + NO, RabbitMQConfig.ROUTING_KEY, book, correlationData);
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.CONFIRM_EXCHANGE + NO, RabbitMQConfig.ROUTING_KEY + NO, book, correlationData);
    }

}
