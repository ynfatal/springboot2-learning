package com.fatal.service;

import com.fatal.entity.Order;

/**
 * Order 服务
 * @author: Fatal
 * @date: 2018/11/2 0002 10:31
 */
public interface IOrderService {

    /**
     * 事务方法内直接调用同一类中的异步方法（异步失效）
     */
    Order saveAndAsyncNoWithProxy(Order order)  throws Exception;

    /**
     * 事务方法内通过代理类调用同一类中的异步方法（异步生效）
     */
    Order saveAndIdenticalClassAsyncWithProxy(Order order)  throws Exception;

    /**
     * 事务方法内直接调用不同类中的异步方法（异步生效）
     */
    Order saveAndDifferentClassAsyncWithProxy(Order order)  throws Exception;

}
