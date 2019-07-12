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

    /**
     * 使用构造方法注入
     */
    @Autowired
    public BookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public void send() {
        Book book = new Book(1L, "六月与便士");
        // 添加延迟队列
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME,
                RabbitMQConfig.DELAY_ROUTING_KEY, book, message -> {
                    /**
                     * @desc: 对消息本身进行单独设置，每条消息的TTL可以不同。
                     * @careful: 如果你在`延迟队列`中配置了params.put("x-message-ttl", 5 * 1000);那么下面
                     *  这句就可以省略，二选一即可
                     */
                    message.getMessageProperties().setExpiration(String.valueOf(5 * 1000));
                    return message;
                });
        log.info("[发送时间] - [{}]", LocalDateTime.now());
    }

}
