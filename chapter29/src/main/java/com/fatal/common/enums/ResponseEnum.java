package com.fatal.common.enums;

import lombok.Getter;

/**
 * @author Fatal
 * @date 2019/8/18 0018 11:10
 */
@Getter
public enum ResponseEnum {

    /**
     * 购物车sku数量超出范围
     */
    SHOP_CART_SKU_COUNT_OUT_OF_RANGE(10000, "购物车sku数量超出范围"),

    /**
     * 购物车已满，不能添加新sku
     */
    SHOP_CART_SKU_TYPE_COUNT_FULL(10001, "购物车已满，不能添加新sku"),

    /**
     * 数据库不存在此sku
     */
    SKU_IS_NOT_EXISTS(10100, "数据库不存在此sku"),

    /**
     * sku已下架
     */
    SKU_IS_OFF_THE_SHELVES(10101, "sku已下架"),

    ;

    private Integer code;

    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
