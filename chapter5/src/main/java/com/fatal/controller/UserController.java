package com.fatal.controller;

import com.fatal.entity.User;
import com.fatal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User 控制器
 * @author: Fatal
 * @date: 2018/9/30 0030 12:00
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public List<User> queryUsers() {
        return userService.queryUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public int delUser(@PathVariable Long id) {
        return userService.delUser(id);
    }

    @PostMapping
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }


    @PutMapping("/{id}")
    public int editUser(@PathVariable Long id, @RequestBody User user) {
        return userService.editUser(id, user);
    }

}
