package com.fatal.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 全名 Stock Keeping Unit(库存量最小单位)，可以理解为有具体规格的商品。例如：40码白色帆布鞋。
 * @author Fatal
 * @date 2019/8/14 0014 17:15
 */
@Data
@Entity
@Accessors(chain = true)
public class Sku implements Serializable {

    /**
     * skuID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 这里为了方便，用自增
    private Long id;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 店铺ID
     */
    private Long shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 商品名称（冗余字段）
     */
    private String goodsName;

    /**
     * sku单价（单位：分）
     */
    private Long price;

    /**
     * sku库存
     */
    private Integer stock;

    /**
     * sku图片
     */
    private String picture;

    /**
     * 规格（规格本来应该是张表的，这里为了方便，就直接 String）
     */
    private String properties;

    /**
     * 允许添加到购物车的最大数额
     */
    private Integer max;

    /**
     * 创建时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    /**
     * sku状态：-1 下架; 0 删除; 1 在架
     */
    private Integer status;

}
