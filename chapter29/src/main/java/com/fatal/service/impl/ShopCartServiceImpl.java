package com.fatal.service.impl;

import com.fatal.common.exception.ValidateException;
import com.fatal.common.constants.ShopCartConstant;
import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartSkuDTO;
import com.fatal.dto.ShopCartItemDTO;
import com.fatal.dto.ShopCartMainDTO;
import com.fatal.common.enums.ResponseEnum;
import com.fatal.service.ISkuService;
import com.fatal.service.IShopCartService;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 购物车服务实现
 * @author Fatal
 * @date 2019/8/15 0015 8:13
 */
@Service
public class ShopCartServiceImpl implements IShopCartService {

    private ISkuService skuService;

    private RedisTemplate<String, Serializable> redisTemplate;

    private HashOperations<String, Object, Object> hashOperations;

    public ShopCartServiceImpl(RedisTemplate<String, Serializable> redisTemplate,
                               ISkuService skuService) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.skuService = skuService;
    }

    /**
     * 操作一：购物车sku项点击 “+”，`购物车sku总数`加一
     * 操作二：购物车sku项输入框填充数字，这个数是总数；前端拿到总数，先将它与商家定义的max比较，
     *      若总数小于 max，则 `总数 - 数据库总数`作为`增量`，输入框显示总数
     *      若总数大于或等于 max，则 `max - 数据库总数`作为`增量`，输入框显示 max
     * 操作三：商品详情页添加到购物车
     * @desc 该方法会对购物车单种sku总个数以及购物车sku种类数量进行控制
     * @param userId 用户ID
     * @param skuId skuID
     * @param increment 增量
     */
    @Override
    public void increment(Long userId, Long skuId, Long increment) {
        // 校验 skuId 是否存在（保证数据库存在该sku）
        skuService.getById(skuId);
        Integer value = (Integer) hashOperations.get(ShopCartConstant.getKey(userId), skuId);
        if (ObjectUtils.isEmpty(value) && hashOperations.size(ShopCartConstant.getKey(userId)) >= ShopCartConstant.TYPE_MAX) {
            throw new ValidateException(ResponseEnum.SHOP_CART_SKU_TYPE_COUNT_FULL);
        }
        boolean overflow = increment > ShopCartConstant.MAX ||
                !ObjectUtils.isEmpty(value) && value + increment > ShopCartConstant.MAX;
        if (overflow) {
            hashOperations.put(ShopCartConstant.getKey(userId), skuId, ShopCartConstant.MAX);
            throw new ValidateException(ResponseEnum.SHOP_CART_SKU_COUNT_OUT_OF_RANGE);
        }
        hashOperations.increment(ShopCartConstant.getKey(userId), skuId, increment);
    }

    /**
     * 操作：购物车sku项点击 “-”，`购物车sku总数`减一
     * @param userId 用户ID
     * @param skuId skuID
     */
    @Override
    public void removeOne(Long userId, Long skuId) {
        // 校验 skuId 是否存在（保证数据库存在该sku）
        skuService.getById(skuId);
        Long value = hashOperations.increment(ShopCartConstant.getKey(userId), skuId, -1L);
        if (value <= 0) {
            // 如果购物车中该sku的数量小于或等于0，就将该sku从购物车中删除
            remove(userId, skuId);
        }
    }

    /**
     * @step
     *   1. 获得 shopCartSkuDTOs
     *   2. 根据 店铺ID 对 shopCartSkuDTOs 进行分组
     *   3. 获得 店铺集合，遍历填充数据
     * @desc 带顺序的购物车列表，店铺顺序由旗下最新添加的商品顺序决定。展示给前端后,他只需要往sku里边填充count
     *      就可以直接拿去展示。
     * @param userId 用户ID
     * @param skuIds 10个id（这个由前端控制，淘宝购物车就是没下拉一次加载`10`个，最后不足的也算一次）
     * @return
     */
    @Override
    public List<ShopCartDTO> shopCarts(Long userId, List<Long> skuIds) {
        List<ShopCartSkuDTO> shopCartSkuDTOs = skuIds.stream()
                .map(skuService::getShopCartSkuById)
                .collect(Collectors.toList());
        Map<Long, List<ShopCartSkuDTO>> shopMap = shopCartSkuDTOs.stream()
                .collect(Collectors.groupingBy(ShopCartSkuDTO::getShopId));
        return shopCartSkuDTOs.stream()
                .distinct()
                .map(ShopCartDTO::of)
                .peek(shopCartDTO -> {
                    List<ShopCartSkuDTO> subShopCartSkuDTOs = shopMap.get(shopCartDTO.getShopId());
                    shopCartDTO.setItems(ShopCartItemDTO.of(subShopCartSkuDTOs));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long userId, Long... skuIds) {
        hashOperations.delete(ShopCartConstant.getKey(userId), skuIds);
    }

    @Override
    public void clear(Long userId) {
        redisTemplate.delete(ShopCartConstant.getKey(userId));
    }

    @Override
    public List<ShopCartMainDTO> into(Long userId) {
        List<ShopCartMainDTO> dtos = hashOperations.entries(ShopCartConstant.getKey(userId))
                .entrySet().stream()
                .map(entry -> new ShopCartMainDTO()
                        .setSkuId(entry.getKey())
                        .setCount(entry.getValue())
                ).collect(Collectors.toList());
        // 逆序，最新的添加的sku放最上面
        Collections.reverse(dtos);
        return dtos;
    }
}
