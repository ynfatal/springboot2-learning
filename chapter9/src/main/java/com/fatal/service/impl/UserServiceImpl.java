package com.fatal.service.impl;

import com.fatal.entity.User;
import com.fatal.mapper.IUserMapper;
import com.fatal.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User 服务实现
 * @author: Fatal
 * @date: 2018/10/4 0004 17:10
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;    // 这里会报错，但并不会影响

    @Override
    public Integer insert(User user) {
        // 进行校验...
        return userMapper.insert(user);
    }

    /**
     * 这个方法中用到了我们开头配置依赖的分页插件 pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可
     * @param pageNum 开始页数
     * @param pageSize 每页显示的数据条数
     * @return
     */
    @Override
    public PageInfo<User> pageSearch(int pageNum, int pageSize) {
        // 进行校验...

        // ====================  普通写法   =====================
        // 将参数传进这个方法就可以实现物理分页了，非常简单
        PageHelper.startPage(pageNum,pageSize);
        // 看语句像查询出整个 List，但是底层对其进行了增强，下面语句执行的时候添加了分页条件了
        List<User> users = userMapper.selectUsers();
        // 把查询结果给 PageInfo，它会帮我们取出当前页的数据
        PageInfo result = new PageInfo(users);

        // ====================   lambda写法  =====================
        PageInfo<User> pageInfo = PageHelper.startPage(pageNum, pageSize)
                .setOrderBy("id desc")
//                .doSelectPageInfo(() -> this.userMapper.selectAll());
                .doSelectPageInfo(userMapper::selectAll);

        // TODO 分页 + 排序 userMapper::selectAll 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        return pageInfo;
    }

}
