package com.fatal.component;

import com.fatal.dao.message.MessageRepository;
import com.fatal.entity.Message;
import com.fatal.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.Future;

/**
 * 异步组件
 * @author: Fatal
 * @date: 2018/11/3 0003 10:38
 */
@Slf4j
@Component
public class AsyncComponent {

    @Autowired
    private MessageRepository messageRepository;

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
                messageStr = "下单成功(异步组件)";
                // 推送逻辑...
                messageLog = "[ --> 消息发送成功](异步组件)";
            } else {
                messageStr = "下单失败";
                messageLog = "[ --> 消息发送失败](异步组件)";
            }
            // 保存数据
            Message message = new Message().setContent(messageStr).setReceiver("接收器");
            Message save = messageRepository.save(message);
//            int i = 1/0; // 在异步组件中测试异步事务
            if (save != null && save.getId() != null) {
                log.info("保存订单结果成功(异步组件)[message = {}]", save);
            } else {
                log.info("保存订单结果失败(异步组件)");
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

}
