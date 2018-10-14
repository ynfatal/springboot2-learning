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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter14ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    /**
     * 测试线程安全
     */
    @Test
    public void testThreadSecurity() {
        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> {
                    Thread thread = Thread.currentThread();
                    log.info("【thread】 = {}", thread.getName());
                    stringRedisTemplate.opsForValue().increment("fatal", 1);
                })
        );
        /*try {
            Thread.sleep(3000);
            // 如果主线程不延迟一丢丢时间的话，那么Redis的连接会在主线程执行完毕后另开线程`Thread-2`关闭，
            // 而当`Thread-2`线程`提前`抢到资源时，redis连接就会马上关闭，导致其它线程redis连接不上。
            // 下面取值的代码会使主线程执行时间延迟而不至于导致其它线程redis连接不上，当然你可以让主线程睡个几秒
            // 你可以试试，把下面的代码注释掉
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        String value = stringRedisTemplate.opsForValue().get("fatal");
        Thread thread = Thread.currentThread();
        log.info("{}【fatal】 = {}", thread.getName(), value);
    }

    /**
     * 测试默认模板
     */
    @Test
    public void testString() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String key = "name";
        ops.set(key, "米琪");
        String name = ops.get(key);
        log.info("测试默认模板【字符串缓存结果】 = {}", name);
    }

    /**
     * 测试自定义模板
     */
    @Test
    public void testUser() {
        ValueOperations<String, Serializable> ops = serializableRedisTemplate.opsForValue();
        User user = new User(1l, "米彩", "18");
        String key = "fatal:user";
        ops.set(key, user);
        User result = (User)ops.get(key);
        log.info("测试自定义模板【对象缓存结果】 = {}", result);
    }

}