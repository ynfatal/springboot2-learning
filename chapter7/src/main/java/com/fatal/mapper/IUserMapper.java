package com.fatal.mapper;

import com.fatal.entity.User;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * User 数据库访问层
 * @author: Fatal
 * @date: 2018/10/3 0003 21:47
 */
@SqlResource("user")
public interface IUserMapper extends BaseMapper<User> {

    /**
     * 根据name查找用户
     * @param name 用户名
     * @return
     */
    List<User> selectByName(String name);

}
