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

    public static Random random = new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        log.info("[----------开始执行任务]");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("[----------执行任务完成] 耗时：" + (end-start) + "毫秒");
        return new AsyncResult<>("----------完成任务");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        log.info("[++++++++++开始执行任务]");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("[++++++++++执行任务完成] 耗时：" + (end-start) + "毫秒");
        return new AsyncResult<>("++++++++++完成任务");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        log.info("[**********开始执行任务]");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("[**********执行任务完成] 耗时：" + (end-start) + "毫秒");
        return new AsyncResult<>("**********完成任务");
    }

}
