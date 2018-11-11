package com.fatal.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 测试`匹配指定类与包`的切面类
 * @author: Fatal
 * @date: 2018/11/10 0010 14:29
 */
@Aspect
//@Component
public class PackageOrClassAspectConfig {

    // 匹配ProductService类里头的所有方法
    @Pointcut("within(com.fatal.service.ProductService)")
    public void pointSpecifyClass() {}

    // 匹配com.fatal.service包及子包下所有类的方法
    @Pointcut("within(com.fatal.service..*)")
    public void pointPackageWithDownward() {}

    @Before("pointPackageWithDownward()")
    public void before() {
        System.out.println("");
        System.out.println("======  before  ======");
    }
}
