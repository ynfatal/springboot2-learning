package com.fatal.enums;

import lombok.Getter;

/**
 * 状态枚举
 * @author Fatal
 * @date 2019/8/15 0015 17:49
 */
@Getter
public enum StatusEnums {

    /**
     * 正常
     */
    NORMAL(1, "NORMAL"),

    /**
     * 已删除
     */
    DELETED(0, "DELETED"),

    /**
     * 下架
     */
    PROHIBIT(-1, "PROHIBIT");

    private Integer code;

    private String message;

    StatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
