package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 购物车商品DTO，包括两个最重要的数据，购物车中的商品ID和对应的数量
 * @author Fatal
 * @date 2019/8/16 0016 23:19
 */
@Data
@Accessors(chain = true)
public class ShopCartMainDTO {

    /**
     * 商品ID
     */
    private Object goodsId;

    /**
     * 商品数量
     */
    private Object count;

}
