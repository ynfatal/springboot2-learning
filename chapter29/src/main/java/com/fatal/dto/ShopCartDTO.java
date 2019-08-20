package com.fatal.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 店铺DTO，包含了`店铺`信息和`购物车sku`信息
 * @author Fatal
 * @date 2019/8/15 0015 19:17
 */
@Data
@Accessors(chain = true)
public class ShopCartDTO {

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 购物车sku集合
     */
    private List<ShopCartItemDTO> items;

    public static ShopCartDTO of(ShopCartSkuDTO shopCartSkuDTO) {
        return new ShopCartDTO()
                .setShopId(shopCartSkuDTO.getShopId())
                .setShopName(shopCartSkuDTO.getShopName());
    }
}
