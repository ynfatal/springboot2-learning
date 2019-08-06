package com.fatal.consumer;

import com.fatal.config.RabbitMQConfig;
import com.fatal.entity.User;
import com.fatal.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

/**
 * RabbitMQ 异常处理消费者
 * @desc: 处理消费失败的消息
 * @author: Fatal
 * @date: 2019/7/12 0012 17:18
 */
@Slf4j
@Component
public class ExceptionHandlingConsumer {

    private Random random = new Random();

    private Channel channel;

    public ExceptionHandlingConsumer(CachingConnectionFactory cachingConnectionFactory) {
        Connection connection = cachingConnectionFactory.createConnection();
        this.channel = connection.createChannel(false);
    }

    /**
     * 定时器判断第三方物流系统是否正常
     * 若正常，则从死信队列拿到消息进行消费
     * @throws Exception
     */
    @Scheduled(fixedDelay = 2000)
    @Transactional(rollbackFor = Exception.class)
    public void exceptionHandlingConsumer() throws Exception {
        // 判断第三方系统是否正常了。
        if (!listener()) {
            log.warn("【exceptionHandlingConsumer 监听第三方物流系统】 - [监听时间] - [{}] 第三方系统异常，重试", LocalDateTime.now());
            return;
        }
        // 主动去队列取消息，并获得 GetResponse
        GetResponse getResponse = channel.basicGet(RabbitMQConfig.EXCEPTION_HANDLING_QUEUE_NAME, false);
        if (!Optional.ofNullable(getResponse).isPresent()) {
            log.info("【exceptionHandlingConsumer 监听队列 {}】 - [监听时间] - [{}] 暂无消息",
                    RabbitMQConfig.EXCEPTION_HANDLING_QUEUE_NAME, LocalDateTime.now());
            return;
        }
        try {
            // 获得消息
            User user = RabbitMQUtil.parseObject(getResponse, User.class);
            log.info("【exceptionHandlingConsumer 监听到消息】 - [操作时间] - [{}] -[{}]", LocalDateTime.now(), user);
            // 模拟业务异常
//            int i = 1/0;
            // 模拟 Ack 失败
            /*int number = random.nextInt(100);
            if (number % 2 == 0) {
                throw new IOException("Ack失败");
            }*/
            channel.basicAck(getResponse.getEnvelope().getDeliveryTag(), false);
        } catch (IOException e) {
            log.error("【exceptionHandlingConsumer Ack失败】 time = {}", LocalDateTime.now());
            e.printStackTrace();
            // 消息将被重新排队
            channel.basicRecover();
            // 抛出异常，让消费端事务回滚
            throw new IOException(e);
        } catch (Exception e) {
            log.error("【exceptionHandlingConsumer 消费失败，业务异常】 time = {}", LocalDateTime.now());
            // 业务出现异常，保存到数据库后并手动 ack
            channel.basicAck(getResponse.getEnvelope().getDeliveryTag(), false);
            // 抛出异常，让消费端事务回滚
            throw new RuntimeException(e);
        }
    }

    /**
     * 模拟监听第三方是否正常
     * @return
     */
    private boolean listener() {
        int number = random.nextInt(100);
        if (number % 2 == 0) {
            return true;
        }
        return false;
    }

}