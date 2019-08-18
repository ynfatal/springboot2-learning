package com.fatal.service.impl;

import com.fatal.common.exception.ValidateException;
import com.fatal.constants.ShopCartConstant;
import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartGoodsDTO;
import com.fatal.dto.ShopCartItemDTO;
import com.fatal.dto.ShopCartMainDTO;
import com.fatal.enums.ResponseEnum;
import com.fatal.service.IGoodsService;
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

    private IGoodsService goodsService;

    private RedisTemplate<String, Serializable> redisTemplate;

    private HashOperations<String, Object, Object> hashOperations;

    public ShopCartServiceImpl(RedisTemplate<String, Serializable> redisTemplate,
                               IGoodsService goodsService) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.goodsService = goodsService;
    }

    /**
     * 操作一：购物车商品项点击 “+”，`数据库总数`加一
     * 操作二：购物车商品项输入框填充数字，这个数是总数；前端拿到总数，先将它与商家定义的max比较，
     *      若总数小于 max，则 `总数 - 数据库总数`作为`增量`，输入框显示总数
     *      若总数大于或等于 max，则 `max - 数据库总数`作为`增量`，输入框显示 max
     * 操作三：商品详情添加到购物车
     * @desc 该方法会对购物车单个商品总量进行控制
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @param increment 增量
     */
    @Override
    public void increment(Long userId, Long goodsId, Long increment) {
        Integer value = (Integer) hashOperations.get(getKey(userId), goodsId);
        boolean overflow = increment > ShopCartConstant.MAX ||
                !ObjectUtils.isEmpty(value) && value + increment > ShopCartConstant.MAX;
        if (overflow) {
            hashOperations.put(getKey(userId), goodsId, ShopCartConstant.MAX);
            throw new ValidateException(ResponseEnum.SHOP_CART_GOODS_COUNT_OUT_OF_RANGE.getMessage());
        }
        hashOperations.increment(getKey(userId), goodsId, increment);
    }

    /**
     * 操作：购物车商品项点击 “-”，`数据库总数`减一
     * @param userId 用户ID
     * @param goodsId 商品ID
     */
    @Override
    public void remove(Long userId, Long goodsId) {
        Long value = hashOperations.increment(getKey(userId), goodsId, -1L);
        if (value <= 0) {
            // 如果购物车中该商品的数量小于或等于0，就将该商品从购物车中删除
            delete(userId, goodsId);
        }
    }

    /**
     * @desc 1. 获得 shopCartGoodsDTOs
     *       2. 根据 店铺ID 对 shopCartGoodsDTOs 进行分组
     *       3. 获得 店铺集合，遍历填充数据
     * @param userId 用户ID
     * @param goodsIds 10个id（这个由前端控制，淘宝购物车就是没下拉一次加载`10`个，最后不足的也算一次）
     * @return
     */
    @Override
    public List<ShopCartDTO> shopCarts(Long userId, List<Long> goodsIds) {
        List<ShopCartGoodsDTO> shopCartGoodsDTOs = goodsIds.stream()
                .map(goodsService::getShopCartGoodsById)
                .collect(Collectors.toList());
        Map<Long, List<ShopCartGoodsDTO>> shopMap = shopCartGoodsDTOs.stream()
                .collect(Collectors.groupingBy(ShopCartGoodsDTO::getShopId));
        return shopCartGoodsDTOs.stream()
                .distinct()
                .map(ShopCartDTO::of)
                .peek(shopCartDTO -> {
                    List<ShopCartGoodsDTO> subShopCartGoodsDTOs = shopMap.get(shopCartDTO.getShopId());
                    shopCartDTO.setItems(subShopCartGoodsDTOs.stream()
                        .map(ShopCartItemDTO::of)
                        .collect(Collectors.toList()));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId, Long... goodsIds) {
        hashOperations.delete(getKey(userId), goodsIds);
    }

    @Override
    public void clear(Long userId) {
        redisTemplate.delete(getKey(userId));
    }

    @Override
    public List<ShopCartMainDTO> into(Long userId) {
        List<ShopCartMainDTO> dtos = hashOperations.entries(getKey(userId))
                .entrySet().stream()
                .map(entry -> new ShopCartMainDTO()
                        .setGoodsId(entry.getKey())
                        .setCount(entry.getValue())
                ).collect(Collectors.toList());
        // 逆序，最新的添加的商品放最上面
        Collections.reverse(dtos);
        return dtos;
    }

    private String getKey(Long userId) {
        return String.format(ShopCartConstant.SHOP_CART, userId);
    }
}
