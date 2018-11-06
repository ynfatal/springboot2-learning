package com.fatal.demo;

import com.fatal.dao.message.MessageRepository;
import com.fatal.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试事务方法调用事务方法
 * @author: Fatal
 * @date: 2018/11/6 0006 10:32
 */
@Component
public class Demo1 {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public void test1(Message message) {
        messageRepository.save(message);
        message = new Message().setContent("content_test2")
                .setReceiver("receiver_123456");
        test2(message);
    }

    @Transactional
    void test2(Message message) {
        messageRepository.save(message);
        int i = 1/0; // 抛个异常测试事务
    }


}
