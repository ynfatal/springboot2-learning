package com.fatal.topic;

import com.fatal.topic.sender.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/10/22 0022 15:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TopicApplicationTest {

    @Autowired
    private TopicSender topicSender;

    @Test
    public void send() {
        topicSender.send();
    }

}
