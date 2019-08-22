package com.fatal.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fatal.entity.Goods;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @author Fatal
 * @date 2019/8/14 0014 17:15
 */
@Data
@Accessors(chain = true)
public class GoodsDTO {

    /**
     * 商品ID
     * @desc ID_WORKER: 分布式全局唯一ID 长整型类型
     */
    @TableId(type = IdType.ID_WORKER)
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
     * 商品详情
     */
    private String content;

    /**
     * 商品主图
     */
    private String picture;

    /**
     * 商品状态：-1 下架; 0 删除; 1 在架
     */
    private Integer status;

    public static GoodsDTO of(Goods goods) {
        GoodsDTO dto = new GoodsDTO();
        BeanUtils.copyProperties(goods, dto);
        return dto;
    }

}
