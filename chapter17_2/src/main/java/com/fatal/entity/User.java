package com.fatal.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/10/16 0016 14:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class User implements Serializable {

    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

}