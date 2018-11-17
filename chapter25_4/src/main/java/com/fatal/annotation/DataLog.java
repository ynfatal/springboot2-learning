package com.fatal.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义`数据记录`注解
 * 功能：在类，方法或者属性上标注该注解，并赋予中文名称。后台自动通过反射
 * 将中文名称存入数据库
 * @author: Fatal
 * @date: 2018/11/15 0015 11:23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
public @interface DataLog {

    /** 中文名称 */
    String name();

}
