package com.fatal.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 测试异步注解@Async的组件
 * @author: Fatal
 * @date: 2018/10/31 0031 9:47
 */
@Slf4j
@Component
public class Task {

    @Async
    public Future<String> doTaskOne() throws Exception {
        log.info("[----------开始执行任务]");
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("[----------执行任务完成] 耗时：" + (end-start) + "毫秒");
        return new AsyncResult<>("----------完成任务");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        log.info("[++++++++++开始执行任务]");
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("[++++++++++执行任务完成] 耗时：" + (end-start) + "毫秒");
        return new AsyncResult<>("++++++++++完成任务");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        log.info("[**********开始执行任务]");
        long start = System.currentTimeMillis();
        Thread.sleep(new Random().nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("[**********执行任务完成] 耗时：" + (end-start) + "毫秒");
        return new AsyncResult<>("**********完成任务");
    }

    /**
     * 测试异常处理类捕获异步方法抛出的异常
     */
    @Async
    public void asyncReturnVoidWithException(String param) {
        log.info("[测试异步方法(void)中处理异常,parameter={}]", param);
        throw new RuntimeException(param);
    }


    @Async
    public Future<String> asyncReturnFutureWithException(String param) {
        log.info("[测试异步方法(Future<String>)中处理异常,parameter={}]", param);
        Future<String> future;
        try {
            // Thread.sleep(10000); // 测试Future的get(x,y)方法
            throw new RuntimeException(param);
        } catch (Exception e) {
            log.info("success to catch: [{}]", param);
            future = new AsyncResult<String>(param);
        }
        return future;
    }
}
