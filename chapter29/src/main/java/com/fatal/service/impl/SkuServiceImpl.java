package com.fatal.service.impl;

import com.fatal.common.enums.ResponseEnum;
import com.fatal.common.enums.StatusEnums;
import com.fatal.common.exception.ValidateException;
import com.fatal.dto.ShopCartSkuDTO;
import com.fatal.entity.Sku;
import com.fatal.repository.SkuRepository;
import com.fatal.service.ISkuService;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Fatal
 * @date 2019/8/14 0014 18:22
 */
@Service
public class SkuServiceImpl implements ISkuService {

    private SkuRepository skuRepository;

    public SkuServiceImpl(SkuRepository skuRepository) {
        this.skuRepository = skuRepository;
    }

    /**
     * sku详情
     * @desc 这里的缓存在更新或者删除该sku的时候清理。
     *      为什么这里查找的是上架和下架两个状态的sku。因为下架的sku也需要展示
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames = "entity:sku", key = "#id")
    public Sku getById(Long id) {
        return Optional.ofNullable(skuRepository.findByIdAndStatusIsNot(id, StatusEnums.DELETED.getCode()))
                .orElseThrow(() -> new ValidateException(ResponseEnum.SKU_IS_NOT_EXISTS));
    }

    /**
     * 用于展示的数据。如果还涉及到其他表的数据，可在这里封装。
     * @desc 这里的缓存在更新或者删除该sku的时候清理
     * @param id
     * @return
     */
    @Override
    public ShopCartSkuDTO getShopCartSkuById(Long id) {
        Sku sku = proxy().getById(id);
        return ShopCartSkuDTO.of(sku);
    }

    private ISkuService proxy() {
        return (SkuServiceImpl) AopContext.currentProxy();
    }

}
