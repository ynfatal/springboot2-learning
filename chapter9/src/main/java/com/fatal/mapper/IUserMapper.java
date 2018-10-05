package com.fatal.mapper;

import com.fatal.entity.User;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * 继承 BaseMapper<T> 就可以了，是不是有点类似 JpaRepository
 * User 映射接口
 * @author: Fatal
 * @date: 2018/10/4 0004 17:05
 */
public interface IUserMapper extends BaseMapper<User> {

    List<User> selectUsers();

}
