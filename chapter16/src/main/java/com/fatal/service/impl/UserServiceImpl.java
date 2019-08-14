package com.fatal.service.impl;

import com.fatal.dao.IUserDao;
import com.fatal.dto.ParamDTO;
import com.fatal.entity.User;
import com.fatal.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private StringRedisTemplate template;

    @Override
    @CachePut(cacheNames = "user", key = "#user.id")
    public User insertOrUpdate(User user) {
        // 健壮性判断...
        log.info("进入【insertOrUpdate】方法");
        return dao.insertOrUpdate(user);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "user", key = "#id")
    })
    public User remove(Long id) {
        // 健壮性判断...
        log.info("进入【remove】方法");
        // 删除相关的数据，用户都不存在了，那相关的那些缓存留着干嘛
        removeByPrefix(id);
        return dao.remove(id);
    }

    @Override
    @Cacheable(unless="#result == null", cacheNames = "user", key = "#id")
    public User selectById(Long id) {
        // 健壮性判断...
        log.info("进入【selectById】方法");
        return dao.selectById(id);
    }

    /**
     * 查询集合、分页要不要做缓存，可以根据参数的复杂程度，如果参数是条件单一（比如只有id），则适合做缓存，
     * 如果参数是一个DTO，里边的属性随着变化，出现了很多种情况（数学的`组合`问题），这中场景下要不要做缓存，
     * 可以根据实际情况分析再做决定，对，一定要分析。下面以DTO作为参数进行讨论：
     * 比如：DTO 有三个属性，分别为 1、2、3，那么出现的组合就有（因为位置是确定的，所以就变成`组合`问题）
     * 1、、、
     * 1、2、、
     * 1、2、3、
     * 1、、3、
     * 、2、、
     * 、2、3、
     * 、、3、
     * 三个属性就有7种情况，也就是一个实体对应7种，7 种只是根据位置是否有值
     * 假设1有10个值，2有3个值，3有2个值
     * 那么所有的可能为 7 * 10 * 3 * 2 = 420 种情况，即通过查询这个方法，累计会生成 420 条缓存（临界值）。
     * 如果这些缓存使用频率高的话，那么一定要用。（减少数据库负担嘛）
     * 如果频率偏低的话，那用不用根据实际情况吧。（缓存条数，查询结果的大小等等）
     * 结论：
     * 根据频率决定吧，如果查询条件相同的有一定的几率，建议使用。
     * @return
     */
    @Override
    @Cacheable(unless="#result == null", cacheNames = "users", key = "'users'")
    public List<User> listUser() {
        // 健壮性判断...
        log.info("进入【listUser()】方法");
        return dao.listUser();
    }

    @Override
    @Cacheable(unless="#result == null", cacheNames = "users", key = "#paramDTO.id + ':' + #paramDTO.username")
    public List<User> listUser(ParamDTO paramDTO) {
        // 健壮性判断...
        log.info("进入【listUser(ParamDTO paramDTO)】方法");
        return dao.listUser(paramDTO);
    }

    /**
     * 模糊删除多条件生成的缓存
     * @desc redis 命令没有模糊删除命令。所以使用了 keys * 模糊匹配出需要删除的Set<key>，然后全部删除
     * @param id
     */
    private void removeByPrefix(Long id) {
        Set<String> keys = template.keys("users::" + id + ":*");
        template.delete(keys);
    }

    /**
     *  @Cacheable 根据方法的请求参数对其结果进行缓存
     *  @CachePut 根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
     *  @CachEvict 根据条件对缓存进行清空
     */

}
