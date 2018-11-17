package com.fatal.enums;

public enum ActionType {

    INSERT("添加", 1),

    UPDATE("更新", 2),

    DELETE("删除", 3);

    private String name;

    private int code;

    ActionType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static String getName(int code) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getCode() == code) {
                return actionType.name;
            }
        }
        return null;
    }

    public static Integer getCode(String name) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getName().equals(name)) {
                return actionType.code;
            }
        }
        return null;
    }

    public static ActionType getActionType(int code) {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.getCode() == code) {
                return actionType;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
