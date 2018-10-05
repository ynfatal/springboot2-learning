package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {

    private Integer id;

    private String userName;

    private String password;

    private String phone;

}