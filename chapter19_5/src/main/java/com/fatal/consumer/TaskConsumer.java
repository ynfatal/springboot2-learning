package com.fatal.consumer;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.User;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author: Fatal
 * @date: 2019/7/12 0012 9:05
 */
@Slf4j
@Component
public class TaskConsumer {

    private Random random = new Random();

    @RabbitListener(queues = RabbitMQConfig.TASK_QUEUE)
    @Transactional(rollbackFor = Exception.class)
    public void taskConsumer(@Payload User user, Message message, Channel channel) throws Exception {
        log.info("【taskConsumer 消费消息】 - [消费时间] - [{}] - [{}]", LocalDateTime.now(), user);
        try {
            // 模拟业务异常
//            int i = 1/0;
            // 模拟 Ack 失败
            int number = random.nextInt(100);
            if (number % 2 == 0) {
                throw new IOException("taskConsumer Ack失败");
            }
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO Ack失败的后续处理
            log.error("【taskConsumer Ack失败】 time = {}", LocalDateTime.now());
            // 消息转移
            basicNack(message, channel);
            // 抛出异常，让消费端事务回滚
            throw new IOException(e);
        } catch (Exception e) {
            // TODO 业务异常的后续处理
            log.error("【taskConsumer 消费失败，业务异常】 time = {}", LocalDateTime.now());
            // 消息转移
            basicNack(message, channel);
            // 抛出异常，让消费端事务回滚
            throw new RuntimeException(e);
        }
    }


    /**
     * 将消费失败的信息转移到死信队列
     * @param message
     * @param channel
     * @throws IOException
     */
    private void basicNack(Message message, Channel channel) throws IOException {
        try {
            // 模拟 Nack 失败
            int number = random.nextInt(100);
            if (number % 2 == 0) {
                throw new IOException();
            }
            /**
             * 拒绝消息。multiple 设置为 true，如果调用此方法过程中报错了，消息就会变为 unacked 状态，那么下次调用就需要
             * 拒绝传递标签之前（包括提供的传递标签）的所有消息（当然，如果只有一条消费失败的消息运气不好，因为网络问题调用不了
             * basicNack 方法，那么这条消息就会变成 unacked 状态，那怎么办呢？目前我的想法是给消息设置个过期时间）
             * @method void basicNack(long deliveryTag, boolean multiple, boolean requeue) throws IOException
             * @deliveryTag 指定队列要拒绝的已接收消息的标签（也叫传递标签）。新的队列默认的传递标签为0，代表接收过0条消息；
             *      队列接收消息后，传递标签会从0开始累加。（传递标签de值也可以看成该队列接收的第n条消息）
             * @multiple true: 用于拒绝提供的传递标签之前（包括提供的传递标签）指向的所有消息；
             *      false: 仅拒绝提供的传递标签指向的那条消息
             * @requeue true: 拒绝的消息重新排队
             *      false: 判断该队列是否有绑定死信交换机，没有则丢弃；有则转移到死信交换机做后续处理
             * @desc basicNack 与 basicReject 的唯一区别：basicNack 可以选择拒绝传递标签之前（包括提供的传递标签）的所有消息
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, false);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("【taskConsumer Nack失败】 time = {}", LocalDateTime.now());
            /**
             * basicRecover 在这里解决消费端ack和nack失败和断网问题。怎么说呢？
             *  1. ack和nack失败 通过重新排队，直到这两个方法正常调用为止
             *  2. 断网问题 消费失败的消息会变成 unacked，那么当网络正常了，系统重新启动会再次消费 这个 unacked 的消息。
             * @method Basic.RecoverOk basicRecover(boolean requeue) throws IOException;
             * @requeue true: 消息将被重新排队并可能传递给其他使用者（默认）
             *      false: 消息将被重新排队并传递给同一使用者
             */
            channel.basicRecover();
        }
    }
}
