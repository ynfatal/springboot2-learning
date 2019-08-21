package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用于测试
 * @author Fatal
 * @date 2019/8/19 0019 10:25
 */
@Data
@Accessors(chain = true)
public class RedisTestDTO implements Serializable {

    private Long skuId;

    private Integer count;

    private Long shopId;

}
