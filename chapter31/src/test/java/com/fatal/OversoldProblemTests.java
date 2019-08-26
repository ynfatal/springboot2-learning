package com.fatal;

import com.fatal.service.IBusinessService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Java 模拟超卖
 * @desc 刚开始我还在想，AtomicInteger 这种类的所有方法才都具有原子性，那我之前和数据库交互的实体属性都
 *      只是包装类，那并发情况下对实体属性的操作是非线程安全的，那会不会引起线程安全问题？后来一想，考虑线程
 *      安不安全体现在共享数据上面，实体的属性get出来后是局部变量（局部嘛，多个线程的同一个方法的局部变量互
 *      不影响的），没有必要考虑线程安全与否，从实体属性的角度出发，不需要用原子类。真正需要考虑线程安全的是
 *      对数据库的操作，数据库中的数据当然是共享数据，笑了。这里的疑问应该就是概念混淆了。不过可以想象一下，
 *      AtomicInteger 所有方法具有原子性，数据库的所有语句也具有原子性，AtomicInteger <-> 数据库 ？
 *      我们倒可以用 AtomicInteger 来模拟对数据库的操作呢。于是接下来就偷个懒了，不建数据库，new 个
 *      AtomicInteger来做Demo就行。
 * @author Fatal
 * @date 2019/8/23 0023 10:52
 */
public class OversoldProblemTests extends Chapter31ApplicationTests {

    // 设置库存为10，AtomicInteger的所有方法都具有原子性
    private AtomicInteger stock = new AtomicInteger(10);

    // 模拟一百并发
    private CountDownLatch start = new CountDownLatch(100);
    private CountDownLatch end = new CountDownLatch(100);

    // 创建定长线程池，可控制最大并发数，超出的线程会在队列中等待
    private ExecutorService executorService = Executors.newFixedThreadPool(100);

    @Autowired
    private IBusinessService businessService;

    /**
     * Java 模拟超卖问题
     */
    @Test
    public void oversold() throws InterruptedException {
        IntStream.range(0, 100).forEach(i ->
            executorService.execute(() -> {
                start.countDown();
                try {
                    start.await();
//                    businessService.businessWithoutLock(stock);
                    // Redis分布式锁解决超卖问题
                    businessService.businessWithLock(stock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            })
        );
        end.await();
        System.out.println("剩余库存：" + stock);
    }

}
