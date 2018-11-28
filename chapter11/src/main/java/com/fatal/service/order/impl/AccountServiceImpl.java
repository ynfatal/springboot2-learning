package com.fatal.service.order.impl;

import com.fatal.mapper.AccountMapper;
import com.fatal.service.IAccountService;
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

    @Override
    @Transactional
    public void update() {
        accountMapper.update(90d, 1);//用户1减10块 用户2加10块
        Integer i = 1 / 0;
        accountMapper.update(110d, 2);
    }

}
