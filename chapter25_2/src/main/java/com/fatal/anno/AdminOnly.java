package com.fatal.anno;

import java.lang.annotation.*;

/**
 * 自定义注解（方法级别）
 * @author: Fatal
 * @date: 2018/11/10 0010 16:43
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)   // 指定目标对象为`Class`级别
public @interface AdminOnly {

}
