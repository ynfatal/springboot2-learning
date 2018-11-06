package com.fatal.test;

import com.fatal.demo.Demo1;
import com.fatal.demo.Demo2;
import com.fatal.demo.Demo3;
import com.fatal.entity.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

/**
 * @author: Fatal
 * @date: 2018/11/6 0006 10:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TwoAnnotationTests {

    @Autowired
    private Demo1 demo1;

    @Autowired
    private Demo2 demo2;

    @Autowired
    private Demo3 demo3;

    private Message message;

    @Before
    public void before() {
        message = new Message().setContent("content_test1")
                .setReceiver("receiver_123456");
    }

    /**
     * 测试事务方法调用事务方法
     * 结果：第二个事务方法报错，两条记录的回滚了。所以两个事务方法相互调用还是属于同一个事务
     * 因为，在第二次调用的时候，它遇到@Transactional后会判断之前是否有事务，如果有则不开事务，
     * 用现有的，没有的话则新开一个事务
     */
    @Test
    public void fun() {
        demo1.test1(message);
    }

    /**
     * 测试异步方法调用事务方法
     * 结果：事务方法不起左右，报错没有回滚
     */
    @Test
    public void fun2() throws Exception {
        Future<Message> messageFuture = demo2.test1(message);
        System.out.println(messageFuture.get());
    }

    /**
     * 异步方法调用异步方法
     * 结果：第二个@Async失去作用
     */
    @Test
    public void fun3() throws Exception {
        Future<Message> messageFuture = demo3.test1(message);
        System.out.println(messageFuture.get());
    }

}
