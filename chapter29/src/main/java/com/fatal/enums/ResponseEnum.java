package com.fatal.enums;

import lombok.Getter;

/**
 * @author Fatal
 * @date 2019/8/18 0018 11:10
 */
@Getter
public enum ResponseEnum {

    /**
     * 购物车商品数量超出范围
     */
    SHOP_CART_GOODS_COUNT_OUT_OF_RANGE(10000, "购物车商品数量超出范围");

    private Integer code;

    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
