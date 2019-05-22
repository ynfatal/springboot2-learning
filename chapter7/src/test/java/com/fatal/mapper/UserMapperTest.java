package com.fatal.mapper;

import com.fatal.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Fatal
 * @date: 2019/5/22 0022 16:17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private IUserMapper userMapper;

    @Test
    public void lambdaQueryTest() {
        List<User> users = userMapper.createLambdaQuery()
                .andEq(User::getName, "fatal")
                .andEq(User::getPassword, "123456")
                .select();
        System.out.println(users);
    }

}