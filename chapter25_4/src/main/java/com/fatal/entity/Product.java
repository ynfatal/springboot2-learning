package com.fatal.entity;

import com.fatal.annotation.DataLog;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
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

    private String category;

    private BigDecimal buyPrice;

    private BigDecimal sellPrice;

    private String provider;

    private Date updateTime;

    private Date onlineTime;

}
