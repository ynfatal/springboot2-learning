package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/10/14 0014 17:05
 */
@Data
@Accessors(chain = true)
public class User {

    private Long id;
    private String username;
    private String password;

}
