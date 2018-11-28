package com.fatal.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * Book 实体
 * @author: Fatal
 * @date: 2018/11/27 0027 16:56
 */
@Data
public class Book {

    private Long id;

    @NotBlank(message = "name 不能为空")
    @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间")
    private String name;

    @NotNull(message = "price 不允许为空")
    @DecimalMin(value = "0.1", message = "价格不能低于 {value}")
    private BigDecimal price;

    @Valid // 嵌套验证必须用@Valid
    @NotNull(message = "readers 不能为空")
    @Size(min = 1, message = "readers 至少要有一个自定义属性")
    private List<Reader> readers;

}
