package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User 实体
 * 自动生成数据表
 * @author: Fatal
 * @date: 2018/9/30 0030 21:36
 */
@Data
@Accessors(chain = true)
@Entity(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增，如果没有`@GeneratedValue`，则需我们手动添加 id
    private Long id;

    private String username;

    private String password;

    private String email;

}
