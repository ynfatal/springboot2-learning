package com.fatal.demo;

import com.fatal.mapper.message.MessageRepository;
import com.fatal.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 测试异步方法调用异步方法
 * @author: Fatal
 * @date: 2018/11/6 0006 10:42
 */
@Component
public class Demo3 {

    @Autowired
    private MessageRepository messageRepository;

    @Async
    public Future<Message> test1(Message message) throws Exception {
        System.out.println("当前线程" + Thread.currentThread().getName());
        return test2(message);
    }

    @Async
    Future<Message> test2(Message message) throws Exception {
        System.out.println("当前线程" + Thread.currentThread().getName());
        Message save = messageRepository.save(message);
        return new AsyncResult<>(message);
    }

}
