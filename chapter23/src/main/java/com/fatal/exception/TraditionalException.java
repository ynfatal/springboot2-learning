package com.fatal.exception;

/**
 * 测试传统定制json数据异常
 * @author: Fatal
 * @date: 2018/10/30 0030 14:08
 */
public class TraditionalException extends RuntimeException {

    public TraditionalException() {
        super("这是一个测试传统定制json数据的异常");
    }

}
