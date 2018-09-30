package com.fatal.service;

import com.fatal.entity.User;

import java.util.List;

/**
 * @author: Fatal
 * @date: 2018/9/30 0030 14:26
 */
public interface IUserService {

    public List<User> queryUsers();
    public User getUser(Long id);
    public int delUser(Long id);
    public int addUser( User user);
    public int editUser( Long id, User user);

}
