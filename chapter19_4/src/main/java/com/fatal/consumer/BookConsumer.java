package com.fatal.consumer;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.Book;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Book 消费者
 * @author: Fatal
 * @date: 2018/10/23 0023 14:16
 */
@Slf4j
@Component
public class BookConsumer {

    @RabbitListener(queues = RabbitMQConfig.REGISTER_QUEUE_NAME)
    public void listenerDelayQueue(Book book, Message message, Channel channel) {
        log.info("[listenerDelayQueue 监听的消息] - [消费时间] - [{}] - [{}]", LocalDateTime.now(), book);
        try {
            // 通知`MQ`消息已被成功消费了，可以Ack了,rabbitmq收到ack后，将远程队列中的消息删除
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // TODO 如果报错了,那么我们可以进行`容错`处理,比如转移当前消息进入其它队列
        }
    }

}
