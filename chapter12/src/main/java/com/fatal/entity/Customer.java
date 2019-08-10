package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Customer 实体
 * @author Fatal
 * @date 2019/8/9 0009 8:15
 * @desc 在类上加上`SpringData Mongodb 的 @Document`，可以更改该集合的名称
 */
@Data
@Accessors(chain = true)
@Document(collection = "customer")
public class Customer {

    /**
     * 该 id 主要供 mongodb 内部使用
     */
    private String id;

    private String name;

    private Date birthday;

    private Integer age;

    private String hobby;

    private String phone;

}
