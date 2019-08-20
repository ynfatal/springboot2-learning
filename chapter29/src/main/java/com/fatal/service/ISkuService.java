package com.fatal.service;

import com.fatal.dto.ShopCartSkuDTO;
import com.fatal.entity.Sku;

/**
 * @author Fatal
 * @date 2019/8/14 0014 18:21
 */
public interface ISkuService {

    /**
     * 根据skuID查找sku
     * @param id
     * @return
     */
    Sku getById(Long id);

    /**
     * 根据skuID查询购物车skuDTO
     * @param id
     * @return
     */
    ShopCartSkuDTO getShopCartSkuById(Long id);

}
