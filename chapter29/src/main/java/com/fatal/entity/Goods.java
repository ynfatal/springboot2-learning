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
 * @author Fatal
 * @date 2019/8/14 0014 17:15
 */
@Entity
@Data
@Accessors(chain = true)
public class Goods implements Serializable {

    /**
     * 商品ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 这里为了方便，用自增
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
    @Column(columnDefinition = "text")
    private String content;

    /**
     * 商品主图
     */
    private String picture;

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
     * 商品状态：-1 下架; 0 删除; 1 在架
     */
    private Integer status;

}
