package com.fatal.entity;

import com.fatal.enums.ActionType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * `操作记录`实体
 * @author: Fatal
 * @date: 2018/11/15 0015 11:13
 */
@Data
@Accessors(chain = true)
public class Action {

    private String id;

    /** 操作对象的id，这里指的是Product的id */
    private Long objectId;

    /** 操作对象的Class，这里指的是Product的Class */
    private String objectClass;

    /** 操作人 */
    private String operator;

    /** 操作时间 */
    private Date operateTime;

    /** 操作类型 */
    private ActionType actionType;

    /** `操作细项`集合 */
    private List<ChangeItem> changes = new ArrayList<>();

}
