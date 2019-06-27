package com.fatal;

import com.fatal.convert.ConvertUtil;
import com.fatal.dto.UserDTO;
import com.fatal.entity.User;
import com.fatal.mapper.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

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

    /**
     * 测试分页查询
     */
    @Test
    public void findPage() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("username")));
        Page<User> page = repository.findAll(pageable);
        log.info("[分页+排序+查询所有] - [{}]", page.getContent());
    }

    /**
     * 测试分页查询（内容替换）
     */
    @Test
    public void findPageWithConvertContent() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("username")));
        Page<User> page = repository.findAll(pageable);
        // 内容转为DTO
        Page<UserDTO> result = page.map(ConvertUtil::convert);
        log.info("[分页+排序+查询所有(DTO)] - [{}]", result.getContent());
    }

    /**
     * 测试自定义分页查询sql
     */
    @Test
    public void findPageWithCustomSql() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.asc("id")));
        Page<User> page = repository.findPage(pageable);
        log.info("[分页+排序+查询所有（自定义分页查询sql）] - [{}]", page.getContent());
    }

    /**
     * 测试动态拼装sql加模糊查询
     */
    @Test
    public void findPageWithLike() {
        String username1 = "米";
        String username2 = "";
        String username3 = null;
        List<User> list1 = repository.findPageWithLike(username1);
        List<User> list2 = repository.findPageWithLike(username2);
        List<User> list3 = repository.findPageWithLike(username3);
        System.out.println("list1: ");
        list1.forEach(System.out::println);
        System.out.println("list2: ");
        list2.forEach(System.out::println);
        System.out.println("list3: ");
        list3.forEach(System.out::println);
    }
}
