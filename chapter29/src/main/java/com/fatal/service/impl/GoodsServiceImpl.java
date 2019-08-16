package com.fatal.service.impl;

import com.fatal.dto.ShopCartGoodsDTO;
import com.fatal.entity.Goods;
import com.fatal.enums.StatusEnums;
import com.fatal.repository.GoodsRepository;
import com.fatal.service.IGoodsService;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author Fatal
 * @date 2019/8/14 0014 18:22
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    private GoodsRepository goodsRepository;

    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    /**
     * 测试缓存
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames = "entity:goods", key = "#id")
    public Goods getById(Long id) {
        Example<Goods> example = Example.of(new Goods()
                .setId(id)
                .setStatus(StatusEnums.NORMAL.getCode()));
        return goodsRepository.findOne(example)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    @Cacheable(cacheNames = "shop:cart:goods", key = "#id")
    public ShopCartGoodsDTO getShopCartGoodsById(Long id) {
        Goods goods = proxy().getById(id);
        return ShopCartGoodsDTO.of(goods);
    }

    private IGoodsService proxy() {
        return (GoodsServiceImpl) AopContext.currentProxy();
    }

}
