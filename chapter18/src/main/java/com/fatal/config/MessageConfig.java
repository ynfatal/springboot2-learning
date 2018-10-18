package com.fatal.config;

import com.fatal.message.ReceiverOne;
import com.fatal.message.ReceiverTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * `消息服务`配置类
 * `@Bean`修饰含参方法时，参数从Spring容器中取
 * @author: Fatal
 * @date: 2018/10/18 0018 15:40
 */
@Configuration
public class MessageConfig {

    /**
     * 往容器注入`消费者One`和`消费者Two`
     */
    @Bean
    ReceiverOne receiverOne(CountDownLatch latch) {
        return new ReceiverOne(latch);
    }

    @Bean
    ReceiverTwo receiverTwo(CountDownLatch latch) {
        return new ReceiverTwo(latch);
    }

    @Bean
    CountDownLatch countDownLatch() {
        return new CountDownLatch(1);
    }


    @Bean
    RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory,
                                                            MessageListenerAdapter adapterOne,
                                                                MessageListenerAdapter adapterTwo) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        /** 设置连接工厂 */
        container.setConnectionFactory(factory);
        /** 添加两个消息监听器，设置监听的主题为`topic` */
        container.addMessageListener(adapterOne, new PatternTopic("topic"));
        container.addMessageListener(adapterTwo, new PatternTopic("topic"));
        return container;
    }

    @Bean
    MessageListenerAdapter adapterOne(ReceiverOne receiverOne) {
        /** 对`ReceiverOne`进行封装，指定接受消息de方法为`receiverMessage` */
        return new MessageListenerAdapter(receiverOne, "receiveMessage");
    }

    @Bean
    MessageListenerAdapter adapterTwo(ReceiverTwo receiverTwo) {
        /** 对`ReceiverTwo`进行封装，指定接受消息de方法为`receiverMessage` */
        return new MessageListenerAdapter(receiverTwo, "receiveMessage");
    }

}
