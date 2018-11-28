package com.fatal.entity;

import com.fatal.annotation.DateTime;
import lombok.Data;

/**
 * Student 实体
 * @author: Fatal
 * @date: 2018/11/28 0028 11:48
 */
@Data
public class Student {

    private String name;

    private Integer age;

    @DateTime(format = "yyyy-MM-dd HH:mm", message = "您输入的格式错误，正确的格式为：{format}")
    private String birthday;

}
