package com.fatal.demo;

import com.fatal.mapper.message.MessageRepository;
import com.fatal.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

/**
 * 测试异步方法调用事务方法
 * @author: Fatal
 * @date: 2018/11/6 0006 10:42
 */
@Component
public class Demo2 {

    @Autowired
    private MessageRepository messageRepository;

    @Async
    public Future<Message> test1(Message message) throws Exception {
        Message msg = test2(message);
        return new AsyncResult<>(msg);
    }

    @Transactional
    Message test2(Message message) {
        Message save = messageRepository.save(message);
        int i = 1/0; // 抛个异常测试事务
        return save;
    }

}
