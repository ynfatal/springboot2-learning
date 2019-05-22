package com.fatal.controller;

import com.fatal.entity.User;
import com.fatal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Fatal
 * @date: 2018/10/3 0003 21:56
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 测试自带的查询功能
     */
    @GetMapping("/{id}")
    public User fatal(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return user;
    }

    /**
     * 测试自定义sql
     */
    @GetMapping("/query/{name}")
    public List<User> fatal(@PathVariable String name) {
        List<User> users = userService.selectByName(name);
        return users;
    }

}
