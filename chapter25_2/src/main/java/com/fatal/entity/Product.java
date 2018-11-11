package com.fatal.entity;

import com.fatal.anno.NeedSecured;

/**
 * 当切点为 @Pointcut("@args(com.fatal.anno.NeedSecured) && within(com.fatal..*)") 时
 * 标注`@NeedSecured`的实体Product作为参数时会走切面逻辑
 * @author: Fatal
 * @date: 2018/11/10 0010 17:53
 */
@NeedSecured
public class Product {
}
