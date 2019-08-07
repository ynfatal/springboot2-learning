package com.fatal.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 测试`匹配参数`的切面
 * @author: Fatal
 * @date: 2018/11/10 0010 16:18
 */
@Aspect
//@Component
public class ArgsAspectConfig {

    // 匹配指定类中任何以find开头而且只有一个Long参数的方法
    @Pointcut("args(Long) && within(com.fatal.service.*)")
    public void pointArgsWithOneParam() {}

    // 匹配指定类中任何以find开头而且只有一个Long参数的方法
    @Pointcut("execution(* *..find*(Long))")
    public void pointExecutionWithOneParam() {}

    // 匹配指定类中任何以find开头的而且第一个参数为Long型的方法
    @Pointcut("args(Long,..) && within(com.fatal.service.*)")
    public void pointArgsWithGEOneParam() {}

    // 匹配指定类中任何以find开头的而且第一个参数为Long型的方法
    @Pointcut("execution(* *..find*(Long,..))")
    public void pointExecutionWithGEOneParam() {}

    @Before("pointExecutionWithGEOneParam()")
    public void before() {
        System.out.println("");
        System.out.println("======  before  ======");
    }

}
