package com.fatal.controller;

import com.fatal.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * User 控制器
 * @author: Fatal
 * @date: 2018/10/17 0017 15:07
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/")
    @ApiOperation(value = "查询所有", notes = "获取用户列表")
    public List<User> get() {
        List<User> users = Arrays.asList(new User(0L, "米彩", "123"),
                new User(1L, "米琪", "123"));
        return users;
    }

    @PostMapping("/")
    @ApiOperation(value = "添加用户", notes = "添加用户操作")
    public User insert(@ApiParam(name = "user", value = "用户信息") User user) {
        return user;
    }

}
