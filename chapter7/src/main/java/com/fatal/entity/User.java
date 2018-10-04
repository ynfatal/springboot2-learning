package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Fatal
 * @date: 2018/10/3 0003 21:40
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Integer id;
    private String name;
    private String password;
    private Date createTime;

}
