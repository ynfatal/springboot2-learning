package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/10/15 0015 11:23
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;

}
