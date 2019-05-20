package com.fatal.mapper.message;

import com.fatal.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/11/2 0002 11:20
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageRepositoryTest {

    @Autowired
    private MessageRepository repository;

    @Test
    public void fun() {
        Message message = new Message().setContent("消息内容").setReceiver("接收器");
        Message save = repository.save(message);
        log.info("发送消息成功[order = {}]", save);
    }

}