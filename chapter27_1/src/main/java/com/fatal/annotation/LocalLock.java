package com.fatal.annotation;

import java.lang.annotation.*;

/**
 * 本地锁
 * @author: Fatal
 * @date: 2018/11/29 0029 9:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited // 指明该注解是可以被继承的
public @interface LocalLock {

    /**
     * 本地锁的key
     */
    String key() default "";

}
