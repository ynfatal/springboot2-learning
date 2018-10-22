package com.fatal.fanout;

import com.fatal.fanout.sender.FanoutSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/10/22 0022 17:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FanoutApplicationTest {

    @Autowired
    private FanoutSender sender;

    @Test
    public void send() {
        sender.send();
    }

}
