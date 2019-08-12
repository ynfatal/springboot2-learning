package com.fatal.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 适用于修饰数量（比如：金额，单位为分）
 * @desc 值必须大于或等于 0
 * @author Fatal
 * @date 2019/8/12 0012 11:01
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@NotNull
@Min(0)
@Constraint(validatedBy = { })
@ReportAsSingleViolation
public @interface Amount {

    String message() default "不能为null且不能小于0";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
