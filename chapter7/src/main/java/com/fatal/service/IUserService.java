package com.fatal.service;

import com.fatal.entity.User;

import java.util.List;

/**
 * @author: Fatal
 * @date: 2018/10/3 0003 21:54
 */
public interface IUserService {

    User getUserById(Integer id);

    List<User> select(String name);
}
