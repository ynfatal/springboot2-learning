package com.fatal.service.impl;

import com.fatal.component.AsyncComponent;
import com.fatal.mapper.message.MessageRepository;
import com.fatal.mapper.order.OrderRepository;
import com.fatal.entity.Message;
import com.fatal.entity.Order;
import com.fatal.service.order.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: Fatal
 * @date: 2018/11/2 0002 10:34
 */
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AsyncComponent asyncComponent;

    /**
     * 事务方法内直接调用同一类中的异步方法（异步失效）
     */
    @Override
    @Transactional
    public Order saveAndAsyncNoWithProxy(Order order) throws Exception {
        Order save = orderRepository.save(order);
        // 推送消息
        if (save != null) {
            Future<Object> future = sendMessage(save);
            Object o = future.get(5000, TimeUnit.SECONDS);
            dealWithFuture(future);
        }
        log.info(Thread.currentThread() + "[ --> 下单结束：mark]");
        return save;
    }

    /**
     * 事务方法内通过代理类调用同一类中的异步方法（异步生效）
     */
    @Override
    @Transactional
    public Order saveAndIdenticalClassAsyncWithProxy(Order order) throws Exception {
        Order save = orderRepository.save(order);
//        int i = 1/0; // 测试事务
        // 推送消息
        if (save != null) {
            // 获得当前代理对象
            OrderServiceImpl orderService = (OrderServiceImpl) AopContext.currentProxy();
            log.info("OrderServiceImpl代理对象为：[{}]", orderService.getClass());
            log.info("OrderServiceImpl对象为：[{}]", this.getClass());
            // 调用异步方法获得调用结果，并对结果进行处理
            Future<Object> future = orderService.sendMessage(save);
            dealWithFuture(future);
        }
        log.info(Thread.currentThread() + "[ --> 下单结束：mark]");
        return save;
    }

    /**
     * 事务方法内直接调用不同类中的异步方法（异步生效）
     */
    @Override
    @Transactional
    public Order saveAndDifferentClassAsyncWithProxy(Order order) throws Exception {
        Order save = orderRepository.save(order);
//        int i = 1/0; // 测试事务
        // 推送消息
        if (save != null) {
            // 调用异步方法获得调用结果，并对结果进行处理
            Future<Object> future = asyncComponent.sendMessage(save);
            dealWithFuture(future);
        }
        log.info(Thread.currentThread() + "[ --> 下单结束：mark]");
        return save;
    }

    /**
     * 异步推送消息
     */
    @Async
    @Transactional
    public Future<Object> sendMessage(Order order) {
        Future<Object> future = null;
        Long id = order.getId();
        String messageStr = "";
        String messageLog = "";
        try {
            if (id != null) {
                messageStr = "下单成功";
                // 推送逻辑...
                messageLog = "[ --> 消息发送成功]";
            } else {
                messageStr = "下单失败";
                messageLog = "[ --> 消息发送失败]";
            }
            // 保存数据
            Message message = new Message().setContent(messageStr).setReceiver("接收器");
            Message save = messageRepository.save(message);
//            int i = 1/0; // 测试异步事务
            if (save != null && save.getId() != null) {
                log.info("保存订单结果成功[message = {}]", save);
            } else {
                log.info("保存订单结果失败");
            }
            log.info(Thread.currentThread() + messageLog);
            future = new AsyncResult<>(Thread.currentThread().getName() + messageLog);
        } catch (Exception e) {
            future = new AsyncResult<>(new RuntimeException(e));
            // 异步方法出现异常后 --> 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println("~~~~~~~~~~~~~~  " + Thread.currentThread().getName()+ " -- 异步方法抛异常啦    ~~~~~~~~~~~~~~");
            e.printStackTrace();
        } finally {
            return future;
        }
    }

    /**
     * 异步结果出来
     */
    private void dealWithFuture(Future future) throws Exception {
        Object o = future.get(5000, TimeUnit.SECONDS);
        if (o instanceof Exception) {
            Exception e = (Exception) o;
            System.out.println("~~~~~~~~~~~~~~  " + Thread.currentThread().getName()+ " -- 事务方法抛异常啦    ~~~~~~~~~~~~~~");
            e.printStackTrace();
            // 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            /**
             * 抛出 RuntimeException 让它自动回滚（需要全局异常处理器统一出来的话则抛个异常咯
             * ，不需要的话就手动回滚吧）
             */
//            throw new RuntimeException(e);
        } else if (o instanceof String){
            log.info("[异步方法成功返回] --> {}", o);
        }
    }

}
