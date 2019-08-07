package com.fatal.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 测试`Advice`的切面
 * @author: Fatal
 * @date: 2018/11/11 0011 20:39
 */
@Aspect
@Component
public class AdviceAspectConfig {

    /*// =========================    after    =========================

    @Pointcut("@annotation(com.fatal.anno.AdminOnly)")
    public void afterPoint() {}

    @After("afterPoint()")
    public void after() {
        System.out.println("======  after  ======");
        System.out.println();
    }

    // =========================    afterReturning    =========================

    @Pointcut("within(com.fatal.service.sub..*Service)")
    public void afterReturningPoint() {}

    @AfterReturning("afterReturningPoint()")
    public void afterReturning() {
        System.out.println("======  afterReturning  ======");
        System.out.println();
    }

    // =========================    afterThrowing    =========================

    @Pointcut("execution(public * com.fatal.service.ProductService.*(..) throws *)")
    public void afterThrowingPoint() {}

    @AfterThrowing("afterThrowingPoint()")
    public void afterThrowing() {
        System.out.println("======  afterThrowing  ======");
        System.out.println();
    }*/

    // =========================    around    =========================

    @Pointcut("execution(public * com.fatal.service..*Service.*(..))")
    public void aroundPoint() {}

    /**
     * @param joinPoint:获取当前执行的方法
     */
    @Around("aroundPoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        System.out.println("======  before  ======");
        try {
            // 获得返回值
            result = joinPoint.proceed(joinPoint.getArgs());
            System.out.println("方法返回值为：" + result);
            System.out.println("======  afterReturning  ======");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("======  afterThrowing  ======");
        } finally {
            System.out.println("======  after  ======");
        }
        System.out.println();
        return result;
    }

}
