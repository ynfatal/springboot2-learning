package com.fatal;

import com.fatal.entity.User;
import com.fatal.mapper.IUserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter9ApplicationTests {

    @Autowired
    private IUserMapper userMapper;

    @Test
    public void insert() {
        User user = new User().setUsername("micai").setPassword("123").setPhone("123");
        int record = userMapper.insert(user);
        Assert.assertNotEquals(0 ,record);
    }

    @Test
    public void selectUsers() {
        List<User> users = userMapper.selectUsers();
        print(users);
        Assert.assertNotEquals(0,users.size());
    }

    @Test
    public void selectOne() {
        User user = userMapper.selectOne(new User().setId(1000));
        Assert.assertNotEquals(null ,user);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        int record = userMapper.updateByPrimaryKeySelective(new User().setId(1000).setPassword("123123123"));
        Assert.assertNotEquals(0 ,record);
    }

    @Test
    public void updateByPrimaryKey() {
        User user = userMapper.selectOne(new User().setId(1003));
        user.setUsername("米琪");
        int record = userMapper.updateByPrimaryKey(user);
        Assert.assertNotEquals(0 ,record);
    }

    private void print(List list) {
        list.forEach(System.out::println);
    }

}
