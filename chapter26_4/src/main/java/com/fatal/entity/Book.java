package com.fatal.entity;

import com.fatal.annotation.Amount;
import lombok.Data;

/**
 * @author Fatal
 * @date 2019/8/12 0012 10:52
 */
@Data
public class Book {

    private Long id;

    /**
     * 价格（单位：分）
     */
    @Amount(message = "price 不能为空且不能小于0")
    private Integer price;

}
