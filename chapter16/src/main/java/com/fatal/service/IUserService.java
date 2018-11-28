package com.fatal.service;

import com.fatal.entity.User;

/**
 * User 服务
 * @author: Fatal
 * @date: 2018/10/14 0014 17:28
 */
public interface IUserService {

    /** 增或改 */
    User insertOrUpdate(User user);

    /** 删 */
    User remove(Long id);

    /** 查 */
    User selectById(Long id);

}
