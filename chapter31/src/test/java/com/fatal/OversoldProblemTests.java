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
