package com.fatal.common.exception;

import javax.validation.ConstraintViolationException;

/**
 * 校验异常
 * @author Fatal
 * @date 2019/8/18 0018 11:00
 */
public class ValidateException extends ConstraintViolationException {

    public ValidateException(String message) {
        super(message, null);
    }

}
