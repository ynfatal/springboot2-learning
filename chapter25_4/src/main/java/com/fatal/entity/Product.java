package com.fatal.entity;

import com.fatal.annotation.DataLog;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Produt 实体
 * @author: Fatal
 * @date: 2018/11/15 0015 10:56
 */
@Data
@Entity
@Accessors(chain = true)
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @DataLog(name = "产品名称")
    private String name;

    @DataLog(name = "商品类别")
    private String category;

    @DataLog(name = "商品详情")
    private String detail;

    @DataLog(name = "买入价")
    private Integer buyPrice;

    @DataLog(name = "卖出价")
    private Integer sellPrice;

    @DataLog(name = "供应商")
    private String provider;

    @DataLog(name = "更新时间")
    private Date updateTime;

    @DataLog(name = "上线时间")
    private Date onlineTime;

}
