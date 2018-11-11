package com.fatal.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 测试`匹配对象`的切面
 * @author: Fatal
 * @date: 2018/11/10 0010 15:16
 */
@Aspect
//@Component
public class ObjectAspectConfig {

    /**
     * this: 可以拦截 DeclareParents(Introduction)
     * target: 不拦截 DeclareParents(Introduction)
     */
    // 匹配Loggable接口生成的组件的代理对象的方法
    @Pointcut("this(com.fatal.log.Loggable)")
    public void pointThis() {}

    // 匹配Loggable接口生成的组件的`被代理对象`的方法
    @Pointcut("target(com.fatal.log.Loggable)")
    public void pointTarget() {}

    // 匹配组件名为`logService2`的bean里边的方法
    // 注意：括号里边为bean的id。默认为类名`首字母小写`
    @Pointcut("bean(logService2)")
    public void pointSpecifyBean() {}

    // 匹配所有以Service结尾的bean里头的方法
    @Pointcut("bean(*Service)")
    public void pointRangeBean() {}

    @Before("pointRangeBean()")
    public void before() {
        System.out.println("");
        System.out.println("======  before  ======");
    }

}
