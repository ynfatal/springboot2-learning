package com.fatal.many2one;

import com.fatal.many2one.sender.SenderOne;
import com.fatal.many2one.sender.SenderTwo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/10/22 0022 11:56
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Many2OneApplicationTest {

    @Autowired
    private SenderOne senderOne;

    @Autowired
    private SenderTwo senderTwo;

    @Test
    public void send() {
        // 两发布者发布消息
        senderOne.send();
        senderTwo.send();
    }

}
