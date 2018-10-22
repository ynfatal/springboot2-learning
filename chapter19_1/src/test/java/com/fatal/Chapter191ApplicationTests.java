package com.fatal;

import com.fatal.sender.FatalSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter191ApplicationTests {

    @Autowired
    private FatalSender sender;

    @Test
    public void send() {
        // 发布消息
        sender.send();
    }

}
