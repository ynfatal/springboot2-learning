package com.fatal.dao.impl;

import com.fatal.dao.IUserDao;
import com.fatal.dto.ParamDTO;
import com.fatal.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User 数据库访问层实现
 * @author: Fatal
 * @date: 2018/10/14 0014 17:11
 */
@Repository
public class UserDaoImpl implements IUserDao {

    /** 模拟数据库 */
    private static Map<Long, User> db = new HashMap<>();

    static {
        // 初始化数据库
        db.put(1L,new User().setId(1L).setUsername("米彩").setPassword("18"));
        db.put(2L,new User().setId(2L).setUsername("米琪").setPassword("19"));
        db.put(3L,new User().setId(3L).setUsername("小米").setPassword("20"));
    }

    @Override
    public User insertOrUpdate(User user) {
        if (user.getId() == null) {
            user.setId(System.currentTimeMillis());
        }
        db.put(user.getId(), user);
        return user;
    }

    @Override
    public User remove(Long id) {
        return db.remove(id);
    }

    @Override
    public User selectById(Long id) {
        return db.get(id);
    }

    @Override
    public List<User> listUser() {
        List<User> users = db.values().stream()
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public List<User> listUser(ParamDTO paramDTO) {
        return db.values().stream()
                .filter(user -> {
                    // 模拟多条件查询
                    Long id = paramDTO.getId();
                    String username = paramDTO.getUsername();
                    if (!ObjectUtils.isEmpty(id) && !ObjectUtils.isEmpty(username)) {
                        return id.equals(user.getId()) && username.equals(user.getUsername());
                    } else if (!ObjectUtils.isEmpty(id)) {
                        return id.equals(user.getId());
                    } else if (!ObjectUtils.isEmpty(username)){
                        return username.equals(user.getUsername());
                    } else {
                        return true;
                    }
                })
                .collect(Collectors.toList());
    }

}
