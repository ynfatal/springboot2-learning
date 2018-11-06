package com.fatal.test;

import com.fatal.entity.Order;
import com.fatal.service.order.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试 @Transactional
 * @author: Fatal
 * @date: 2018/11/2 0002 10:30
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionalTests {

    @Autowired
    private IOrderService orderService;

    private Order order;

    @Before
    public void before() {
        order = new Order().setOrderId("ORDER_123456")
                .setUserId("USER_123456")
                .setAmount(100000l)
                .setPhone("137504123456");
    }

    /**
     * 事务方法内直接调用同一类中的异步方法（异步失效）
     * 注掉 int i = 1/0;
     */
    @Test
    public void saveAndAsyncNoWithProxy() throws Exception {
        Order save = orderService.saveAndAsyncNoWithProxy(order);
        log.info("新增订单成功[order = {}]", save);
    }

    /**
     * 事务方法内通过代理类调用同一类中的异步方法（异步生效）
     * 测试1：不打开 int i = 1/0; 测试 @Async是否起作用
     * 测试2：打开 int i = 1/0; 测试 @Transactionl + @Async 异步事务
     */
    @Test
    public void saveAndIdenticalClassAsyncWithProxy() throws Exception {
        Order save = orderService.saveAndIdenticalClassAsyncWithProxy(order);
        log.info("新增订单成功[order = {}]", save);
    }

    /**
     * 事务方法内直接调用不同类中的异步方法（异步生效）
     * 测试1：不打开 int i = 1/0; 测试@Async是否起作用
     * 测试2：打开 int i = 1/0; 测试 @Transactionl + @Async 异步事务
     */
    @Test
    public void saveAndDifferentClassAsyncWithProxy() throws Exception {
        Order save = orderService.saveAndDifferentClassAsyncWithProxy(order);
        log.info("新增订单成功[order = {}]", save);
    }

}
