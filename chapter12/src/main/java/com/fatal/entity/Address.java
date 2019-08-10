package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 地址 实体
 * @author Fatal
 * @date 2019/8/9 0009 17:58
 */
@Data
@Accessors(chain = true)
@Document(collection = "address")
public class Address {

    /**
     * 该 id 主要供 mongodb 内部使用
     */
    private String id;

    /**
     * @Field 用于与数据库的字段做映射
     */
    @Field(value = "customer_id")
    private String customerId;

    private String address;

}
