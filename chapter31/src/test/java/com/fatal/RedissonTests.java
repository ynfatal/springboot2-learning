package com.fatal;

import org.junit.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author Fatal
 * @date 2019/8/24 0024 17:39
 */
public class RedissonTests extends Chapter31ApplicationTests {

    @Autowired
    private RedissonClient redisson;

    /**
     * 测试整合 Redisson
     * @throws InterruptedException
     */
    @Test
    public void fairLockTest() throws InterruptedException {
        RLock fairLock = redisson.getFairLock("anyLock");
        boolean isLock = fairLock.tryLock(100, 10, TimeUnit.SECONDS);
        if (isLock) {
            Thread.sleep(4000);
            fairLock.unlock();
        }
    }

}
