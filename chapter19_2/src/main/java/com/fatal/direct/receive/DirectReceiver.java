package com.fatal.direct.receive;

import com.fatal.direct.config.DirectRabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author: Fatal
 * @date: 2019/7/7 0007 0:10
 */
@Slf4j
@Component
public class DirectReceiver {

    @Transactional(rollbackFor = Exception.class)
    @RabbitListener(queues = {DirectRabbitConfig.DIRECT_QUEUE})
    public void receive(String messages, Message message, Channel channel) throws Exception {
        try {
            log.info("【DirectReceiver接收到消息】 -- [{}]", messages);
            /**
             * @method void basicAck(long deliveryTag, boolean multiple) throws IOException
             * @deliveryTag 指定队列要确认的已接收消息的标签（也叫传递标签）。新的队列默认的传递标签为0，代表接收过0条消息；
             *      队列接收消息后，传递标签会从0开始累加。（传递标签de值也可以看成该队列接收的第n条消息）
             * @multiple true: 用于确认提供的传递标签之前（包括提供的传递标签）指向的所有消息；
             *      false: 仅确认提供的传递标签指向的那条消息
             */
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
