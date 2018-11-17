package com.fatal.entity;

import lombok.Data;

/**
 * `操作细项`实体
 * @author: Fatal
 * @date: 2018/11/15 0015 11:14
 */
@Data
public class ChangeItem {

    /** 属性名 */
    private String field;

    /** 属性中文名 */
    private String fieldShowName;

    /** 原值 */
    private String oldValue;

    /** 新值 */
    private String newValue;

}
