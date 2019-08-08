package com.fatal.dao;

import com.fatal.entity.Action;
import com.fatal.enums.ActionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author: Fatal
 * @date: 2018/11/15 0015 11:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActionDaoTest {

    @Autowired
    private ActionDao dao;

    private Action action;

    @Before
    public void before() {
        action = new Action()
                .setObjectId(123456L)
                .setActionType(ActionType.INSERT)
                .setObjectClass("objectClass")
                .setOperateTime(new Date())
                .setOperator("fatal");
    }

    @Test
    public void save() {
        Action save = dao.save(action);
        System.out.println("【Action新增成功】 -- [Action = " +  save +"]");
    }

}