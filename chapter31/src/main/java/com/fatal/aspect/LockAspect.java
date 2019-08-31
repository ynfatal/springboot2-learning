package com.fatal.aspect;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        boolean isLock = fairLock.tryLock(4, 2, TimeUnit.SECONDS);
        if (isLock) {
            try {
                return joinPoint.proceed(args);
            } finally {
                fairLock.unlock();
            }
        }
        log.info("{}: 系统繁忙，请重试", Thread.currentThread().getName());
        // 这里为了方便查看日志，把下面一行注释掉了，实际还需要抛个异常就行
//        throw new RuntimeException("系统繁忙，请重试");
        return null;
    }

}
