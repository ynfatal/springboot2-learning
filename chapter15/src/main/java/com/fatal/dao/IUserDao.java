package com.fatal.dao;

import com.fatal.entity.User;

/**
 * User 数据库访问层
 * @author: Fatal
 * @date: 2018/10/14 0014 17:08
 */
public interface IUserDao {

    /** 增或改 */
    User insertOrUpdate(User user);

    /** 删 */
    User remove(Long id);

    /** 查 */
    User selectById(Long id);

}
