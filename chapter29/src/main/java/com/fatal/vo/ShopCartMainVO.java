package com.fatal.vo;

import com.fatal.dto.ShopCartMainDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车skuVO，包括两个最重要的数据，购物车中的skuID和对应的数量
 * @author Fatal
 * @date 2019/8/16 0016 23:19
 */
@Data
@Accessors(chain = true)
public class ShopCartMainVO {

    /**
     * skuID
     */
    private Object skuId;

    /**
     * 购物车sku数量
     */
    private Object count;

    private static ShopCartMainVO of(ShopCartMainDTO shopCartMainDTO) {
        return new ShopCartMainVO()
                .setSkuId(shopCartMainDTO.getSkuId())
                .setCount(shopCartMainDTO.getCount());
    }

    public static List<ShopCartMainVO> of(List<ShopCartMainDTO> shopCartMainDTOs) {
        return shopCartMainDTOs.stream()
                .map(ShopCartMainVO::of)
                .collect(Collectors.toList());
    }

}
