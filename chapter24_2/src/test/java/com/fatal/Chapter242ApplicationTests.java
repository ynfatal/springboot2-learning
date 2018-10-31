package com.fatal;

import com.fatal.component.Task;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter242ApplicationTests {

    @Autowired
    private Task task;

    /**
     * 测试使用自定义异步线程池
     */
    @Test
    public void testPool() throws Exception {

        Future<String> taskOne = task.doTaskOne();
        Future<String> taskTwo = task.doTaskTwo();
        Future<String> taskThree = task.doTaskThree();

        long start = System.currentTimeMillis();
        while (true) {
            // 判断异步方法是否执行完成
            if (taskOne.isDone() && taskTwo.isDone() && taskThree.isDone()) {
                break;
            }
        }

        long end = System.currentTimeMillis();
        log.info("任务全部完成，总耗时：{} 毫秒", (end - start));
    }


    /**
     * 测试异常处理类
     */
    @Test
    public void testAsyncExceptionReturnVoid() {
        task.asyncReturnVoidWithException("testAsyncExceptionReturnVoid");
    }

    /**
     * 测试Future<String>处理异常
     */
    @Test
    public void testAsyncExceptionReturnFuture() throws InterruptedException,
            ExecutionException, TimeoutException {
        Future<String> future = task.asyncReturnFutureWithException("testAsyncExceptionReturnFuture");
        /**
         * V get(long timeout, TimeUnit unit)
         * timeout: 值
         * unit: 单位
         * 表示超过此时间会抛出超时异常，该线程就被释放会线程池，从而防止线程阻塞
         */
        log.info("[在返回类型为Future<String>异步方法里捕获的异常] {}", future.get(5, TimeUnit.SECONDS));
    }

}
