package com.fatal.service.impl;

import com.fatal.entity.User;
import com.fatal.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: Fatal
 * @date: 2018/10/14 0014 17:32
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private IUserService service;

    @Test
    public void insertOrUpdate() {
        User user = new User().setUsername("micai").setPassword("18");
        User result = service.insertOrUpdate(user);
        log.info("【添加成功】 user -> {}", result);

        result.setUsername("miqi");
        result = service.insertOrUpdate(result);
        log.info("【修改成功】 user -> {}", result);
    }

    @Test
    public void remove() {
        User result = service.remove(1l);
        log.info("【删除成功】 user -> {}", result);
    }

    /**
     * 使用该方法即可测试缓存的存在了
     */
    @Test
    public void selectById() {
        User user = new User().setUsername("micai").setPassword("18");
        User result = service.insertOrUpdate(user);
        log.info("【添加成功】 user -> {}", result);

        /**
         *      因为在添加的时候已经把对象放到缓存中了，所以下面方法执行后，@Cacheable判断出了有缓存，
         *  直接从缓存中拿数据，没进入方法体。所以没显示`进入【selectById】方法`
         */
        User select = service.selectById(result.getId());
        log.info("【查询成功】 user -> {}", select);
    }

}