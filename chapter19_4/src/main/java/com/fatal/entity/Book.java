package com.fatal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Book 实体类
 * @author: Fatal
 * @date: 2018/10/23 0023 12:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private Long id;
    private String name;

}
