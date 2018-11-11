package com.fatal.anno;

import java.lang.annotation.*;

/**
 * 自定义注解（CLASS环境，类级别，支持继承）
 * @author: Fatal
 * @date: 2018/11/10 0010 16:56
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)   // Class 级别
@Inherited
public @interface NeedSecuredClass {
}
