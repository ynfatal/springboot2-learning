package com.fatal.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * 消费者One
 * @author: Fatal
 * @date: 2018/10/18 0018 15:34
 */
@Slf4j
public class ReceiverOne {

    private CountDownLatch latch;

    /**
     * 使用`@Autowired`的Setter方法注入方式
     */
    @Autowired
    public ReceiverOne(CountDownLatch latch) {
        this.latch = latch;
    }

    /** 方法名随你起，不过必须和配置类中的消息监听器组件保持一致 */
    public void receiveMessage(String message) {
        log.info("【消费者 ReceiverOne】：我消费了<" + message + ">");
        latch.countDown();
    }

}
