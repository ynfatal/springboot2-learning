package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Address 数据传输对象
 * @author Fatal
 * @date 2019/8/9 0009 18:32
 */
@Data
@Accessors(chain = true)
public class AddressDTO {

    private String id;

    @Field(value = "customer_id")
    private String customerId;

    private String address;

}
