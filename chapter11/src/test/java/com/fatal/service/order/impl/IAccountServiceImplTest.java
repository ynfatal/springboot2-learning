package com.fatal.service.order.impl;

import com.fatal.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/8/19 0019 21:08
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IAccountServiceImplTest {

    @Autowired
    private IAccountService IAccountService;

    @Test
    public void update() {
        IAccountService.update();
    }
}