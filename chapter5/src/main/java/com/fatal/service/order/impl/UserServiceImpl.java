package com.fatal.service.order.impl;

import com.fatal.dao.IUserDao;
import com.fatal.entity.User;
import com.fatal.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Fatal
 * @date: 2018/9/30 0030 14:27
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> queryUsers() {
        return userDao.queryUsers();
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public int delUser(Long id) {
        return userDao.delUser(id);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public int editUser(Long id, User user) {
        return userDao.editUser(id, user);
    }
}
