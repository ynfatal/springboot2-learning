package com.fatal.controller;

import com.fatal.entity.User;
import com.fatal.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User 控制器
 * @author: Fatal
 * @date: 2018/10/4 0004 17:12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 添加（通用 Mapper 中的方法）
     * @param user
     */
    @PostMapping("/")
    public int insert(User user) {
        // 进行校验...
        return userService.insert(user);
    }

    /**
     * 分页查询（使用分页插件）
     * @param pageNum
     * @param pageSize
     */
    @GetMapping("/")
    public Object pageSearch(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        // 当前页的详细信息
        PageInfo<User> data = userService.pageSearch(pageNum, pageSize);
        return data;
    }

}
