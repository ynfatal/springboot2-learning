package com.fatal.consumer;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.Book;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Book 消费者
 * 同时监听两个队列
 * @author: Fatal
 * @date: 2018/10/20 0020 10:04
 */
@Slf4j
@Component
public class BookConsumer {

    /**
     * 消费者监听队列`DEFAULT_BOOK_QUEUE`
     *     注：该方案是 spring-boot-data-amqp 默认的方式,不太推荐。具体推荐使用  listenerManualAck()
     * 默认情况下,如果没有配置手动ACK, 那么Spring Data AMQP 会在消息消费完毕后自动帮我们去ACK
     * 存在问题：如果报错了,消息不会丢失,但是会无限循环消费,一直报错,如果开启了错误日志很容易就吧磁盘空间耗完
     * 解决方案：手动ACK,或者try-catch 然后在 catch 里面将`错误的消息转移到其它的系列中去`
     * spring.rabbitmq.listener.simple.acknowledge-mode=manual
     *
     * @param book 监听的内容
     */
    @RabbitListener(queues = {RabbitMQConfig.DEFAULT_BOOK_QUEUE})
    public void listenerAutoAck(Book book, Message message, Channel channel) {
        log.info("【listenerAutoAck监听的消息】 - [{}]", book);
        try {
            // 通知`MQ`消息已被成功消费了，可以Ack了
            // int i = 1/0;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) { // 其实这里是抛IOException的，为了好测试写成Exception
            try {
                // 处理失败，重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 推荐使用手动Ack
     * 消费者监听队列`MANUAL_BOOK_QUEUE`
     */
    @RabbitListener(queues = {RabbitMQConfig.MANUAL_BOOK_QUEUE})
    public void listenerManualAck(Book book, Message message, Channel channel) {
        log.info("【listenerManualAck监听的消息】 - [{}]", book);
        try {
            // 通知`MQ`消息已被成功消费了，可以Ack了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行`容错`处理,比如转移当前消息进入其它队列
        }
    }

}
