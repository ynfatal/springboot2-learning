package com.fatal;

import com.fatal.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter14ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * RedisTemplate 的泛型默认只能支持 <String, String>
     *     如果你写别的泛型如 <String, Object>，它会提示你 Could not autowire.
     *     所以，我们才需要自定义一个能存储其他类型的模板
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;


    /**
     * 测试线程安全（模拟10000并发）
     */
    @Test
    public void testThreadSecurity() throws InterruptedException {
        // 该闩锁主要用于使main线程处于等待状态，防止提前关闭redis连接
        CountDownLatch latch = new CountDownLatch(10000);
        // 拦住所有线程，等计数为 0 时，同时释放
        CountDownLatch concurrentLatch = new CountDownLatch(10000);
        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        IntStream.range(0, 10000).forEach(i ->
                executorService.execute(() -> {
                    concurrentLatch.countDown();
                    latch.countDown();
                    try {
                        concurrentLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Thread thread = Thread.currentThread();
                    log.info("【thread】 = {}", thread.getName());
                    stringRedisTemplate.opsForValue().increment("fatal", 1);
                })
        );

        // 使当前线程等待直到闩锁计数为零，除非线程被中断。
        latch.await();
        String value = stringRedisTemplate.opsForValue().get("fatal");
        log.info("【fatal】 = {}", value);
    }

    /**
     * 测试模板 -> StringRedisTemplate
     */
    @Test
    public void testStringRedisTemplate() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "name";
        ops.set(key, "米彩");
        String name = ops.get(key);
        log.info("测试模板 -> StringRedisTemplate 【字符串缓存结果】 = {}", name);
    }

    /**
     * 测试默认模板 -> RedisTemplate<String, String>
     */
    @Test
    public void testDefaultRedisTemplate() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String key = "name";
        ops.set(key, "米琪");
        String name = ops.get(key);
        log.info("测试默认模板 -> RedisTemplate<String, String> 【字符串缓存结果】 = {}", name);
    }

    /**
     * 测试自定义模板 -> RedisTemplate<String, Serializable>
     */
    @Test
    public void testUser() {
        ValueOperations<String, Serializable> ops = serializableRedisTemplate.opsForValue();
        User user = new User(1l, "米彩", "18");
        String key = "fatal:user";
        ops.set(key, user);
        User result = (User)ops.get(key);
        log.info("测试自定义模板 -> RedisTemplate<String, Serializable>【对象缓存结果】 = {}", result);
    }

}