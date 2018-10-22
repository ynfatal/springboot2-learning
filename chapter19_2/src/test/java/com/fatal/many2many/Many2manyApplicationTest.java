package com.fatal.many2many;

import com.fatal.many2many.sender.FatalSenderOne;
import com.fatal.many2many.sender.FatalSenderTwo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/10/22 0022 13:46
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Many2manyApplicationTest {

    @Autowired
    private FatalSenderOne senderOne;

    @Autowired
    private FatalSenderTwo senderTwo;

    @Test
    public void send() {
        senderOne.send();
        senderTwo.send();
    }

}
