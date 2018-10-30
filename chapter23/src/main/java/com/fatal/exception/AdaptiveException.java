package com.fatal.exception;

/**
 * 测试定制json数据异常
 * @author: Fatal
 * @date: 2018/10/30 0030 14:08
 */
public class AdaptiveException extends RuntimeException {

    public AdaptiveException() {
        super("这是一个测试定制json数据（保持自适应效果）的异常");
    }

}
