package com.fatal.service;

import com.fatal.entity.User;
import com.github.pagehelper.PageInfo;

/**
 * User 服务
 * @author: Fatal
 * @date: 2018/10/4 0004 17:09
 */
public interface IUserService {

    Integer insert(User user);

    PageInfo<User> pageSearch(int pageNum, int pageSize);

}
