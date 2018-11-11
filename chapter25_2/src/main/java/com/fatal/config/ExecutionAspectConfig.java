package com.fatal.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 测试`匹配方法`的切面
 * @author: Fatal
 * @date: 2018/11/10 0010 18:14
 */
@Aspect
//@Component
public class ExecutionAspectConfig {

    // 匹配任意返回值，service包下的以Service结尾的所有方法
    @Pointcut("execution(public * com.fatal.service.*Service.*(..))")
    public void pointPackage() {}

    // 匹配任意返回值，service包及其子包下的以Service结尾的所有方法
    @Pointcut("execution(public * com.fatal.service..*Service.*(..))")
    public void pointPackageWithDownward() {}

    // 匹配返回String类型，service包及其子包下的以Service结尾的所有方法
    @Pointcut("execution(public String com.fatal.service..*Service.*(..))")
    public void pointPackageWithDownwardReturnString() {}

    // 匹配任意返回值，service包及其子包下的以Service结尾，可能抛出任意异常的所有方法
    @Pointcut("execution(public * com.fatal.service..*Service.*(..) throws *)")
    public void pointPackageWithDownwardThrow() {}

    @Before("pointPackageWithDownwardThrow()")
    public void before() {
        System.out.println("");
        System.out.println("======  before  ======");
    }

}
