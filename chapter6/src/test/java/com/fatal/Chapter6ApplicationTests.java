package com.fatal;

import com.fatal.mapper.UserRepository;
import com.fatal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Chapter6ApplicationTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void save() {
        User user = new User().setUsername("米彩").setPassword("123").setEmail("123@qq.com");
        repository.save(user);
        log.info("[添加成功] - [{}]",user);
    }

    @Test
    public void findAll() {
        List<User> list = repository.findAll();
        log.info("[查询所有] - [{}]", list);
    }

    @Test
    public void findOne() {
        User user = new User().setUsername("张三");
        User one = repository.findOne(Example.of(user)).orElse(null);
        log.info("[根据用户名查询成功] - [{}]", one==null?"无记录":one);
    }

    @Test
    public void findAllWithPage() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("username")));
        final Page<User> users = repository.findAll(pageable);
        log.info("[分页+排序+查询所有] - [{}]", users.getContent());
    }
}
