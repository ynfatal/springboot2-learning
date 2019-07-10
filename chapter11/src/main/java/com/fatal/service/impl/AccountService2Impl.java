package com.fatal.service.impl;

import com.fatal.mapper.AccountMapper;
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
public class AccountService2Impl implements IAccountService2 {

    @Autowired
    private AccountMapper accountMapper;  // 这里会报错，但不影响正常使用

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nest2() {
        // 用户1减10块 用户2加10块
        accountMapper.update(90d, 3);
        Integer i = 1 / 0;
        accountMapper.update(110d, 4);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void nest4() {
        // 用户3减10块 用户4加10块
        accountMapper.update(90d, 3);
        accountMapper.update(110d, 4);
    }
}
