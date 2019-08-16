package com.fatal.dto;

import com.fatal.entity.Goods;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 购物车商品DTO，去掉了重复的属性
 * @desc @EqualsAndHashCode 这里只选择了两个属性，是为了后面去重；先去重后map，可以减少大部分操作。
 * @author Fatal
 * @date 2019/8/15 0015 17:45
 */
@Data
@Accessors(chain = true)
public class ShopCartItemDTO {

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

    public static ShopCartItemDTO of(Goods goods) {
        ShopCartItemDTO dto = new ShopCartItemDTO();
        BeanUtils.copyProperties(goods, dto);
        return dto;
    }

    public static ShopCartItemDTO of(ShopCartGoodsDTO shopCartGoodsDTO) {
        ShopCartItemDTO shopCartItemDTO = new ShopCartItemDTO();
        BeanUtils.copyProperties(shopCartGoodsDTO, shopCartItemDTO);
        return shopCartItemDTO;
    }

}
