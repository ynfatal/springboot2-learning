package com.fatal.enums;

import lombok.Getter;

@Getter
public enum ActionType {

    /**
     * 添加
     */
    INSERT("添加", 1),

    /**
     * 更新
     */
    UPDATE("更新", 2),

    /**
     * 删除
     */
    DELETE("删除", 3);

    private String name;

    private int code;

    ActionType(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
