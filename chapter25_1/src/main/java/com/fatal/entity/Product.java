package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Product 实体
 * @author: Fatal
 * @date: 2018/11/8 0008 11:14
 */
@Data
@Accessors(chain = true)
public class Product {

    private Long id;
    private String name;

}
