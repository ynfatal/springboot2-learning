package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/9/26 0026 21:00
 */
@Data
@Accessors(chain = true)
public class User {

    private String name;

    private Integer age;

}
