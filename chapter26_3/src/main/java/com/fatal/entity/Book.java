package com.fatal.entity;

import com.fatal.groups.Groups;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Book 实体
 * @author: Fatal
 * @date: 2018/11/28 0028 15:23
 */
@Data
public class Book {

    @NotNull(message = "id 不能为空", groups = Groups.Update.class)
    private Long id;

    @NotBlank(message = "name 不能为空", groups = Groups.Default.class)
    private String name;

    @NotNull(message = "price 不能为空", groups = Groups.Default.class)
    private BigDecimal price;

}
