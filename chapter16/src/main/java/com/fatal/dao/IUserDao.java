package com.fatal.dao;

import com.fatal.dto.ParamDTO;
import com.fatal.entity.User;

import java.util.List;

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

    /** 查集合 */
    List<User> listUser();

    /** 根据DTO查询集合 */
    List<User> listUser(ParamDTO paramDTO);

}
