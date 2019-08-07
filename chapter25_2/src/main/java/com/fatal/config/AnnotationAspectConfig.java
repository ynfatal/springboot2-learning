package com.fatal.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 测试`匹配注解`的切面
 * @author: Fatal
 * @date: 2018/11/10 0010 16:41
 */
@Aspect
//@Component
public class AnnotationAspectConfig {

    // 匹配所有标注有`@AdminOnly`注解的方法
    @Pointcut("@annotation(com.fatal.anno.AdminOnly)")
    public void pointAnnotationTargetMethod() {}

    // 匹配所有标注有`@NeedSecuredClass`注解的类的所有方法，要求
    // annotation 的 RetentionPolity 为 CLASS
    @Pointcut("@within(com.fatal.anno.NeedSecuredClass)")
    public void pointWithinAnnotationTargetClassWithInherited() {}

    // 匹配所有标注有`@NeedSecured`注解的类的所有方法，要求
    // annotation 的 RetentionPolity 为 RUNTIME
    @Pointcut("@target(com.fatal.anno.NeedSecured) && within(com.fatal..*)")
    public void pointTargetAnnotationTargetClassWithInherited() {}

    // 匹配传入的参数类标注有`@NeedSecured`注解的方法
    @Pointcut("@args(com.fatal.anno.NeedSecured) && within(com.fatal..*)")
    public void pointArgsAnnotationTargetClassWithInherited() {}

    @Before("pointArgsAnnotationTargetClassWithInherited()")
    public void before() {
        System.out.println("");
        System.out.println("======  before  ======");
    }

}
