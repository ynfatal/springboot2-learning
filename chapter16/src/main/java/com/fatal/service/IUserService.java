package com.fatal.service;

import com.fatal.dto.ParamDTO;
import com.fatal.entity.User;

import java.util.List;

/**
 * User 服务
 * @author: Fatal
 * @date: 2018/10/14 0014 17:28
 */
public interface IUserService {

    /** 修改 */
    User update(User user);

    /** 删 */
    User remove(Long id);

    /** 查 */
    User selectById(Long id);

    /** 查集合 */
    List<User> listUser();

    /** 根据DTO查询集合 */
    List<User> listUser(ParamDTO paramDTO);

    /** 修改（解决低流量下，缓存数据库双写一致性问题） */
    User lowFlowRateWithUpdate(User user);

    /** 修改（解决高并发下，缓存数据库双写一致性问题） */
    User highConcurrencyWithUpdate(User user);

}
