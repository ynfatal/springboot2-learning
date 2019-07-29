package com.fatal.exception;

/**
 * 测试定制json数据异常（不自适应）
 * @author: Fatal
 * @date: 2018/10/30 0030 14:08
 */
public class TraditionalException extends RuntimeException {

    public TraditionalException() {
        super("这是一个测试定制json数据的异常（不自适应）");
    }

}
