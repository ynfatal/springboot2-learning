package com.fatal.common.exception;

import com.fatal.common.enums.ResponseEnum;

import javax.validation.ConstraintViolationException;

/**
 * 自定义校验异常
 * @author Fatal
 * @date 2019/8/18 0018 11:00
 */
public class ValidateException extends ConstraintViolationException {

    public ValidateException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage(), null);
    }

}
