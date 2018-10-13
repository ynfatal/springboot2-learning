package com.fatal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/10/13 0013 14:47
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor  // 反序列化需要无参构造器
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;

}
