package com.fatal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * @author Fatal
 * @date 2019/8/24 0024 20:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Lock {

    /**
     * 锁的名称
     * @return
     */
    String name() default "";

    /**
     * 等待获取锁的最长时间
     * @return
     */
    long waitTime() default 4L;

    /**
     * 授予锁后持有锁的最长时间。该时间根据业务也行量而定
     * @return
     */
    long leaseTime() default 2L;

    /**
     * 时间单位
     * @return
     */
    TimeUnit unit() default TimeUnit.SECONDS;

}
