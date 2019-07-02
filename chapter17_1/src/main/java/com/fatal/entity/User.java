package com.fatal.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/10/17 0017 15:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
@Accessors(chain = true)
public class User {

    private Long id;
    @ApiModelProperty(name = "username", value = "用户名")
    private String username;
    @ApiModelProperty(name = "password", value = "密码")
    private String password;

}
