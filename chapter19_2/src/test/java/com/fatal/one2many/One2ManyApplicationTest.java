package com.fatal.one2many;

import com.fatal.one2many.sender.FatalSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/10/22 0022 11:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class One2ManyApplicationTest {

    @Autowired
    private FatalSender sender;

    @Test
    public void send() {
        sender.send();
    }

}
