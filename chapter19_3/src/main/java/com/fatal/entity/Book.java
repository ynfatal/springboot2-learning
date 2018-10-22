package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Book 实体
 * @author: Fatal
 * @date: 2018/10/20 0020 9:39
 */
@Data
@Accessors(chain = true)
public class Book implements Serializable {

    private String id;
    private String name;

}
