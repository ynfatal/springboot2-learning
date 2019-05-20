package com.fatal.service.impl;

import com.fatal.dao.IUserDao;
import com.fatal.entity.User;
import com.fatal.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * User 服务实现
 * @author: Fatal
 * @date: 2018/10/14 0014 17:28
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao dao;

    @Override
    @CachePut(cacheNames = "user", key = "#user.id")
    public User insertOrUpdate(User user) {
        // 健壮性判断...
        log.info("进入【insertOrUpdate】方法");
        return dao.insertOrUpdate(user);
    }

    @Override
    @CacheEvict(cacheNames = "user", key = "#id")
    public User remove(Long id) {
        // 健壮性判断...
        log.info("进入【remove】方法");
        return dao.remove(id);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public User selectById(Long id) {
        // 健壮性判断...
        log.info("进入【selectById】方法");
        return dao.selectById(id);
    }

    /**
     *  @Cacheable 根据方法的请求参数对其结果进行缓存
     *  @CachePut 根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
     *  @CachEvict 根据条件对缓存进行清空
     */

}
