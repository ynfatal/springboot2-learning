package com.fatal.annotation;

import com.fatal.validator.DateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解：校验 Date 类型的格式
 * 最后两个属性时必须要的。否则会报错
 * @author: Fatal
 * @date: 2018/11/28 0028 11:40
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER}) // 属性和参数上可以使用
@Constraint(validatedBy = DateTimeValidator.class)	// 指定该注解的校验器
public @interface DateTime {

    /**
     * 错误消息  - 关键字段
     *
     * @return 默认错误消息
     */
    String message() default "格式错误";

    /**
     * 格式
     *
     * @return 验证的日期格式
     */
    String format() default "yyyy-MM-dd";

    /**
     * 允许我们为约束指定验证组 - 关键字段（TODO 下一章中会介绍）
     *
     * @return 分组
     */
    Class<?>[] groups() default {};

    /**
     * payload - 关键字段
     *
     * @return 暂时不清楚, 知道的欢迎留言交流
     */
    Class<? extends Payload>[] payload() default {};

}
