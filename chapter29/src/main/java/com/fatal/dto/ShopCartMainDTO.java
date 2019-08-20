package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 购物车skuDTO，包括两个最重要的数据，购物车中的skuID和对应的数量
 * @author Fatal
 * @date 2019/8/16 0016 23:19
 */
@Data
@Accessors(chain = true)
public class ShopCartMainDTO {

    /**
     * skuID
     */
    private Object skuId;

    /**
     * 购物车中sku数量
     */
    private Object count;

}
