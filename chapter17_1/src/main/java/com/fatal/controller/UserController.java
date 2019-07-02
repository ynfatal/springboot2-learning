package com.fatal.controller;

import com.fatal.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

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
    public User insert(User user) {
        return user;
    }

    @PutMapping("/")
    @ApiOperation(value = "更新用户", notes = "更新用户操作")
    public Long update(@ApiParam(name = "id", value = "用户主键") Long id) {
        return id;
    }

}
