package com.fatal;

import org.junit.Test;

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
    private AtomicInteger stock = new AtomicInteger(1000);

    // 模拟一万并发
    private CountDownLatch start = new CountDownLatch(10000);
    private CountDownLatch end = new CountDownLatch(10000);

    // 创建定长线程池，可控制最大并发数，超出的线程会在队列中等待
    private ExecutorService executorService = Executors.newFixedThreadPool(10000);

    /**
     * Java 模拟超卖问题
     */
    @Test
    public void oversold() throws InterruptedException {
        IntStream.range(0, 10000).forEach(i ->
            executorService.execute(() -> {
                start.countDown();
                try {
                    start.await();
                    if (stock.get() > 0) {
                        // 1ms 秒执行业务逻辑。当然这是假设，1ms肯定执行不完的。以此为例子，就算是业务很快完成，也会出现超卖
                        Thread.sleep(1);
                        stock.decrementAndGet();
                    }
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
