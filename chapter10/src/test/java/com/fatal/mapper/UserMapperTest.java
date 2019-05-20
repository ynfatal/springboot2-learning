package com.fatal.mapper;

import com.fatal.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/10/5 0005 16:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void deleteByPrimaryKey() {
        int record = mapper.deleteByPrimaryKey(1001);
        Assert.assertNotEquals(0, record);
    }

    @Test
    public void insert() {
        User user = new User().setUserName("米彩").setPassword("123").setPhone("123");
        int record = mapper.insert(user);
        Assert.assertNotEquals(0, record);
    }

    @Test
    public void insertSelective() {
        User user = new User().setUserName("米彩").setPassword("123");
        int record = mapper.insert(user);
        Assert.assertNotEquals(0, record);
    }

    /**
     * select id, user_name, password, phone from user where id = ?
     */
    @Test
    public void selectByPrimaryKey() {
        User user = mapper.selectByPrimaryKey(1000);
        Assert.assertNotEquals(null, user);
    }

    /**
     * Preparing: update user SET user_name = ?, password = ? where id = ?
     * Parameters: 米彩的姐姐(String), 123(String), 1000(Integer)
     */
    @Test
    public void updateByPrimaryKeySelective() {
        User user = new User().setId(1000).setUserName("米彩的姐姐").setPassword("123");
        int record = mapper.updateByPrimaryKeySelective(user);
        Assert.assertNotEquals(0, record);
    }

    /**
     * Preparing: update user set user_name = ?, password = ?, phone = ? where id = ?
     * Parameters: 米彩的姐姐(String), 123(String), null, 1000(Integer)
     */
    @Test
    public void updateByPrimaryKey() {
        User user = new User().setId(1000).setUserName("米彩的姐姐").setPassword("123");
        int record = mapper.updateByPrimaryKey(user);
        Assert.assertNotEquals(0, record);
    }
}