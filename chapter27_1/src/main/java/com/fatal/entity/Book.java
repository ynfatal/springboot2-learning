package com.fatal.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Book 实体
 * @author: Fatal
 * @date: 2018/11/29 0029 9:42
 */
@Data
public class Book {

    private Long id;

    @NotBlank(message = "name 不能为空")
    private String name;

    @NotNull(message = "price 不能为空")
    private BigDecimal price;

}
