package com.fatal.service.impl;

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
public class AccountServiceImplTest {

    @Autowired
    private IAccountService accountService;

    @Test
    public void update() {
        accountService.update();
    }

    @Test
    public void nest1() {
        accountService.nest1();
    }

    @Test
    public void nest3() {
        accountService.nest3();
    }
}