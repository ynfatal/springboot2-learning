package com.fatal.enums;

import lombok.Getter;

/**
 * @author Fatal
 * @date 2019/8/9 0009 9:35
 */
@Getter
public enum OperationEnum {

    /**
     * 成功
     */
    SUCCESS("success", "成功"),

    /**
     * 失败
     */
    FAIL("fail", "失败");

    private String code;

    private String message;

    OperationEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
