package com.fatal.controller;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Book 控制器
 * @desc: 本Demo会分别发布两条消息到两个队列上，然后由同一个消费者（订阅这两个队列）消费
 * @author: Fatal
 * @date: 2018/10/20 0020 9:46
 */
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public void defaultMessage() {
        Book book = new Book().setId("1").setName("米彩");
        /**
         * 分别发布两条消息到两个Queue
         */
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.DEFAULT_BOOK_QUEUE, book);
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.MANUAL_BOOK_QUEUE, book);

    }

}
