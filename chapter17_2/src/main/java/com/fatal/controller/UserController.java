package com.fatal.controller;

import com.battcn.boot.swagger.model.DataType;
import com.battcn.boot.swagger.model.ParamType;
import com.fatal.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * User 控制器
 * @author: Fatal
 * @date: 2018/10/16 0016 14:10
 */
@Slf4j
@RequestMapping("/users")
@RestController
@Api(tags = "1.1",description = "用户管理", value = "用户管理")
public class UserController {

    @GetMapping("")
    @ApiOperation(value = "条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = DataType.STRING, paramType = ParamType.QUERY),
            @ApiImplicitParam(name = "password", value = "密码", dataType = DataType.STRING, paramType = ParamType.QUERY)
    })
    public User query(String username, String password) {
        log.info("多个参数用  @ApiImplicitParams");
        return new User(1L, username, password);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "主键查询")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = DataType.LONG, paramType = ParamType.PATH)
    public User get(@PathVariable Long id) {
        log.info("单个参数用  @ApiImplicitParam");
        return new User(id, "米琪", "123");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "id", value = "用户编号", dataType = DataType.LONG, paramType = ParamType.PATH)
    public void delete(@PathVariable Long id) {
        log.info("单个参数用  @ApiImplicitParam");
    }

    @PostMapping
    @ApiOperation(value = "添加用户")
    public User post(User user) {
        log.info("如果是 POST PUT 这种带 @RequestBody 的可以不用写 @ApiImplicitParam");
        return user;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改用户")
    public void put(@PathVariable Long id, User user) {
        log.info("如果你不想写 @ApiImplicitParam 那么 swagger 也会使用默认的参数名作为描述信息 ");
    }

}