package com.fatal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Fatal
 * @date 2019/8/14 0014 17:15
 */
@Data
@Accessors(chain = true)
public class Goods {

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
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 商品状态：-1 下架; 0 删除; 1 在架
     */
    private Integer status;

}
