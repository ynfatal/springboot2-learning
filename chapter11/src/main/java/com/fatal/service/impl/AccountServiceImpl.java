package com.fatal.service.impl;

import com.fatal.mapper.AccountMapper;
import com.fatal.service.IAccountService;
import com.fatal.service.IAccountService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Account 服务实现类
 * @author: Fatal
 * @date: 2018/8/19 0019 21:06
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;  // 这里会报错，但不影响正常使用

    @Autowired
    private IAccountService2 accountService2;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update() {
        // 用户1减10块 用户2加10块
        accountMapper.update(90d, 1);
        Integer i = 1 / 0;
        accountMapper.update(110d, 2);
    }

    /**
     * 事务方法nest1 -> 事务方法nest2
     * 探究：事务方法nest2 抛异常，方法1事务是否回滚
     * 结果：1. 同类的事务方法，正常回滚
     *      2. 非同类的事务方法，正常回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nest1() {
        // 用户1减10块 用户2加10块
        accountMapper.update(90d, 1);
        accountMapper.update(110d, 2);
        // 同类的事务方法
//        nest2();
        // 非同类的事务方法
        accountService2.nest2();
        // 测试前面的事务会不会回滚
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nest2() {
        // 用户3减10块 用户4加10块
        accountMapper.update(90d, 3);
        Integer i = 1 / 0;
        accountMapper.update(110d, 4);
    }

    /**
     * 事务方法nest3 -> 事务方法nest4
     * 探究：事务方法nest3 抛异常，方法4事务是否回滚
     * 结果：1. 同类的事务方法，正常回滚
     *      2. 非同类的事务方法，正常回滚
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nest3() {
        // 同类的事务方法
//        nest4();
        // 非同类的事务方法
        accountService2.nest4();
        // 用户1减10块 用户2加10块
        accountMapper.update(90d, 1);
        accountMapper.update(110d, 2);
        Integer i = 1 / 0;
        // 测试前面nest4()方法中的事务会不会回滚
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nest4() {
        // 用户3减10块 用户4加10块
        accountMapper.update(90d, 3);
        accountMapper.update(110d, 4);
    }
}
