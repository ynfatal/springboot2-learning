package com.fatal.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Fatal
 * @date 2019/8/24 0024 20:06
 */
@Component
@Aspect
public class LockAspect {

    @Autowired
    private RedissonClient redisson;

    @Pointcut("@annotation(com.fatal.annotation.Lock)")
    public void point() {}

    @Around("point()")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        AtomicInteger stock = (AtomicInteger) args[0];
        if (stock.get() == 0) {
            System.out.println(Thread.currentThread().getName() + ": 库存不足");
            return null;
        }
        RLock fairLock = redisson.getFairLock("lock");
        boolean isLock = fairLock.tryLock(10, 2, TimeUnit.SECONDS);
        if (isLock) {
            Object result = joinPoint.proceed(args);
            fairLock.unlock();
            return result;
        }
        // 这里可以改为自定义异常
//        throw new RuntimeException("人太多，请重试");
        System.out.println(Thread.currentThread().getName() + ": 人太多，请重试");
        return null;
    }

}
