package com.fatal.vo;

import com.fatal.dto.ShopCartDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 店铺VO，包含了`店铺`信息和`购物车sku`信息
 * @author Fatal
 * @date 2019/8/15 0015 19:17
 */
@Data
@Accessors(chain = true)
public class ShopCartVO {

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
    private List<ShopCartItemVO> items;

    private static ShopCartVO of(ShopCartDTO shopCartDTO) {
        return new ShopCartVO()
                .setShopId(shopCartDTO.getShopId())
                .setShopName(shopCartDTO.getShopName())
                .setItems(ShopCartItemVO.of(shopCartDTO.getItems()));
    }

    public static List<ShopCartVO> of(List<ShopCartDTO> shopCartDTOs) {
        return CollectionUtils.isEmpty(shopCartDTOs) ?
                new ArrayList<>() :
                shopCartDTOs.stream()
                    .map(ShopCartVO::of)
                    .collect(Collectors.toList());
    }
}
