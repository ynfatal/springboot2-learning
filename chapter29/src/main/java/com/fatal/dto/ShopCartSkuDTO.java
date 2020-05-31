package com.fatal.dto;

import com.fatal.entity.Sku;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * 购物车skuDTO，包含了购物车需要显示的所有数据
 * @desc @EqualsAndHashCode 这里只选择了两个属性，是为了后面去重；先去重后map，可以减少大部分操作。
 *      注：这个属于 ISkuService 对外交互的DTO，是必不可少的。
 * @author Fatal
 * @date 2019/8/15 0015 17:45
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"shopId", "shopName"})
public class ShopCartSkuDTO {

    /**
     * skuID
     */
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
    private String goodsName;

    /**
     * sku单价（单位：分）
     */
    private Long price;

    /**
     * sku图片
     */
    private String picture;

    /**
     * 规格（规格本来应该是张表的，这里为了方便，就直接 String）
     */
    private String properties;

    /**
     * 单种sku允许添加到购物车的最大数额
     */
    private Integer max;

    /**
     * sku状态：-1 下架; 0 删除; 1 在架
     */
    private Integer status;

    public static ShopCartSkuDTO of(Sku sku) {
        ShopCartSkuDTO dto = new ShopCartSkuDTO();
        BeanUtils.copyProperties(sku, dto);
        return dto;
    }

}
