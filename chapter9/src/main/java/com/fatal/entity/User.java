package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 与jpa比较（比较易混淆的）
 * 不同点：通用Mapper的@GeneratedValue不加默认自增，而JPA的@GeneratedValue不加默认需要手动添加id
 * 相同点：都需要加上主键注解@Id
 * @author: Fatal
 * @date: 2018/10/4 0004 17:05
 */
@Data
@Accessors(chain = true)
public class User {

    @Id     // 使用通用Mapper必须给主键加上注解@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)    // 自增
    private Integer id;

    private String username;

    private String password;

    private String phone;

}

