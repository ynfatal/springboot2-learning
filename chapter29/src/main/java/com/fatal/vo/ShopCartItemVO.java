package com.fatal.vo;

import com.fatal.dto.ShopCartItemDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车商品VO，去掉了重复的属性
 * @desc @EqualsAndHashCode 这里只选择了两个属性，是为了后面去重；先去重后map，可以减少大部分操作。
 * @author Fatal
 * @date 2019/8/15 0015 17:45
 */
@Data
@Accessors(chain = true)
public class ShopCartItemVO {

    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品单价（单位：分）
     */
    private Long price;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 商品主图
     */
    private String picture;

    /**
     * 允许添加到购物车的最大数额
     */
    private Integer max;

    private static ShopCartItemVO of(ShopCartItemDTO shopCartItemDTO) {
        ShopCartItemVO shopCartItemVO = new ShopCartItemVO();
        BeanUtils.copyProperties(shopCartItemDTO, shopCartItemVO);
        return shopCartItemVO;
    }

    public static List<ShopCartItemVO> of(List<ShopCartItemDTO> shopCartItemDTOs) {
        return shopCartItemDTOs.stream()
                .map(ShopCartItemVO::of)
                .collect(Collectors.toList());
    }

}
