package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Message 订单结果消息实体
 * @author: Fatal
 * @date: 2018/11/2 0002 11:13
 */
@Data
@Accessors(chain = true)
@Entity
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 内容 */
    private String content;

    /** 接收器 */
    private String receiver;

}
