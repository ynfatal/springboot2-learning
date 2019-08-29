package com.fatal.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Fatal
 * @date 2019/8/24 0024 20:06
 */
@Component
@Aspect
@Order(1)
public class LockAspect {

    @Autowired
    private RedissonClient redisson;

    @Pointcut("@annotation(com.fatal.annotation.Lock)")
    public void point() {}

    @Around("point()")
    public Object lock(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        RLock fairLock = redisson.getFairLock("lock");
        boolean isLock = fairLock.tryLock(10, 2, TimeUnit.SECONDS);
        if (isLock) {
            try {
                return joinPoint.proceed(args);
            } finally {
                fairLock.unlock();
            }
        }
//        throw new RuntimeException("系统繁忙，请重试");
        // 这里方便看日志，改成输出，实际抛个异常就行
        System.out.println(Thread.currentThread().getName() + ": 系统繁忙，请重试");
        return null;
    }

}
