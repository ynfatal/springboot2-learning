package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Order 订单实体
 * @author: Fatal
 * @date: 2018/11/2 0002 9:47
 */
@Data
@Accessors(chain = true)
@Entity(name = "t_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 金额 */
    private Long amount;

    /** 订单id */
    private String orderId;

    /** 联系方式 */
    private String phone;

    /** 用户id */
    private String userId;

}
