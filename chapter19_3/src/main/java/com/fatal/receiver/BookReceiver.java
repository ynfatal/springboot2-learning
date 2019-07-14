package com.fatal.receiver;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.Book;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Book 消费者
 * 同时监听两个队列
 * @author: Fatal
 * @date: 2018/10/20 0020 10:04
 */
@Slf4j
@Component
public class BookReceiver {

    /**
     * 监听CONFIRM_QUEUE队列的消息
     * 这个队列会接收来自默认交换机和confirmExchange交换机的消息
     */
    @RabbitListener(queues = {RabbitMQConfig.CONFIRM_QUEUE})
    public void listenerReceiver(Book book, Message message, Channel channel) {
        try {
            log.info("【listenerReceiver 监听的消息】 - [监听时间] - [{}] - [{}]", LocalDateTime.now(), book);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO Ack失败的后续处理
            log.error("【Ack失败】 time = {}", LocalDateTime.now());
        } catch (Exception e) {
            // TODO 业务异常的后续处理
            log.error("【消费失败，业务异常】 time = {}", LocalDateTime.now());
        }
    }
}
