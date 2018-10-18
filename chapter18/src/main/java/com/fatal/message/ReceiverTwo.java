package com.fatal.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * 消费者Two
 * @author: Fatal
 * @date: 2018/10/18 0018 15:34
 */
@Slf4j
public class ReceiverTwo {

    private CountDownLatch latch;

    /**
     * 使用`@Autowired`的Setter方法注入方式
     */
    @Autowired
    public ReceiverTwo(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        log.info("【消费者 ReceiverTwo】：我消费了<" + message + ">");
        latch.countDown();
    }

}
