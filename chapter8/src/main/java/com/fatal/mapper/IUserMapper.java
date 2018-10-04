package com.fatal.mapper;

import com.fatal.entity.User;

import java.util.List;

/**
 * User 映射接口
 * @author: Fatal
 * @date: 2018/10/4 0004 17:05
 */
public interface IUserMapper {

    Integer insert(User record);

    List<User> selectUsers();

}
