package com.fatal.service;

import com.fatal.dto.ShopCartGoodsDTO;
import com.fatal.entity.Goods;

/**
 * @author Fatal
 * @date 2019/8/14 0014 18:21
 */
public interface IGoodsService {

    /**
     * 根据商品ID查找商品
     * @param id
     * @return
     */
    Goods getById(Long id);

    /**
     * 根据商品ID查询购物车商品DTO
     * @param id
     * @return
     */
    ShopCartGoodsDTO getShopCartGoodsById(Long id);

}
