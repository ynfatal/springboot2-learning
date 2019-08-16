package com.fatal.dto;

import com.fatal.entity.Goods;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 购物车商品DTO，包含了购物车需要显示的所有数据
 * @desc @EqualsAndHashCode 这里只选择了两个属性，是为了后面去重；先去重后map，可以减少大部分操作。
 * @author Fatal
 * @date 2019/8/15 0015 17:45
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"shopId", "shopName"})
public class ShopCartGoodsDTO {

    private Long id;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

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

    public static ShopCartGoodsDTO of(Goods goods) {
        ShopCartGoodsDTO dto = new ShopCartGoodsDTO();
        BeanUtils.copyProperties(goods, dto);
        return dto;
    }

}
