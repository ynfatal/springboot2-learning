package com.fatal.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义权限注解
 * @author: Fatal
 * @date: 2018/11/8 0008 11:43
 */
@Retention(RetentionPolicy.RUNTIME) // 注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
@Target(ElementType.METHOD)      // 限定该注解的使用对象为`方法`
public @interface AdminOnly {

}
