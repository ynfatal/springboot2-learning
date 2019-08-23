package com.fatal.service.impl;

import com.fatal.common.constants.ShopCartConstant;
import com.fatal.common.enums.ResponseEnum;
import com.fatal.common.enums.StatusEnums;
import com.fatal.common.exception.ValidateException;
import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartItemDTO;
import com.fatal.dto.ShopCartSkuDTO;
import com.fatal.service.IShopCartService;
import com.fatal.service.ISkuService;
import org.springframework.aop.framework.AopContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.*;
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
     * @desc 该方法会对购物车单种sku总个数以及购物车sku种类数量进行控制，并且维护后面用于分组的购物车信息
     * @param userId 用户ID
     * @param skuId skuID
     * @param increment 增量
     */
    @Override
    public void increment(Long userId, Long skuId, Long increment) {
        // 校验 skuId 是否存在（保证数据库存在该sku，且该sku的状态为正常）
        ShopCartSkuDTO shopCartSkuDTO = skuService.getShopCartSkuById(skuId);
        isNormal(shopCartSkuDTO);
        Integer value = (Integer) hashOperations.get(ShopCartConstant.getCartKey(userId), skuId);
        if (ObjectUtils.isEmpty(value)) {
            hashOperations.put(ShopCartConstant.getGroupingKey(userId), skuId, shopCartSkuDTO.getShopId());
            if (hashOperations.size(ShopCartConstant.getCartKey(userId)) >= ShopCartConstant.TYPE_MAX) {
                throw new ValidateException(ResponseEnum.SHOP_CART_SKU_TYPE_COUNT_FULL);
            }
        }
        boolean overflow = increment > ShopCartConstant.MAX ||
                !ObjectUtils.isEmpty(value) && value + increment > ShopCartConstant.MAX;
        if (overflow) {
            hashOperations.put(ShopCartConstant.getCartKey(userId), skuId, ShopCartConstant.MAX);
            throw new ValidateException(ResponseEnum.SHOP_CART_SKU_COUNT_OUT_OF_RANGE);
        }
        hashOperations.increment(ShopCartConstant.getCartKey(userId), skuId, increment);
    }

    /**
     * 操作：购物车sku项点击 “-”，`购物车sku总数`减一
     * @param userId 用户ID
     * @param skuId skuID
     */
    @Override
    public void removeOne(Long userId, Long skuId) {
        // 校验 skuId 是否存在（保证数据库存在该sku）
        isNormal(skuService.getShopCartSkuById(skuId));
        Long value = hashOperations.increment(ShopCartConstant.getCartKey(userId), skuId, -1L);
        if (value <= 0) {
            // 如果购物车中该sku的数量小于或等于0，就将该sku从购物车中删除
            proxy().remove(userId, skuId);
        }
    }

    /**
     * 获取当前的 skuIds 然后封装数据
     * @param userId 用户ID
     * @param currentPage 当前页数
     * @return
     */
    @Override
    public List<ShopCartDTO> shopCarts(Long userId, Integer currentPage) {
        List<Long> skuIds = proxy().currentPageSkuId(userId, currentPage).stream()
                .map(this::toLong)
                .collect(Collectors.toList());
        return CollectionUtils.isEmpty(skuIds) ? new ArrayList<>() : shopCarts(userId, skuIds);
    }

    /**
     * @Cacheable unless排除对空集和null的缓存
     * @param userId 用户ID
     * @param currentPage 当前页码
     * @return
     */
    @Override
    @Cacheable(unless = "#result == null || #result.size() == 0", cacheNames = ShopCartConstant.SHOP_CART_PAGE,
            key = "#userId + ':' + #currentPage")
    public List<Object> currentPageSkuId(Long userId, Integer currentPage) {
        return proxy().shopCartGrouping(userId).stream()
                .skip((currentPage - 1) * ShopCartConstant.PAGE_SIZE)
                .limit(ShopCartConstant.PAGE_SIZE)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(unless = "#result == null", cacheNames = ShopCartConstant.SORT_SHOP_CART, key = "#userId")
    public List<Object> shopCartGrouping(Long userId) {
        Map<Object, Object> entries = hashOperations.entries(ShopCartConstant.getGroupingKey(userId));
        List<Map.Entry<Object, Object>> entryList = new ArrayList<>(entries.entrySet());
        // 最新加入购物的sku在最后，所以这里得先反序
        Collections.reverse(entryList);
        Map<Object, List<Object>> collect = entryList.stream()
                .collect(
                        Collectors.groupingBy(
                                Map.Entry::getValue,
                                // 得到的key保留原来（作为Entry的value时）的顺序（保存put的顺序）
                                LinkedHashMap::new,
                                // 得到的list元素也要保留顺序 Collectors.toList() -> ArrayList（有序）
                                Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                        )
                );
        return collect.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    /**
     * @step
     *   1. 获得 shopCartSkuDTOs
     *   2. 根据 店铺ID 对 shopCartSkuDTOs 进行分组
     *   3. 获得 店铺集合，遍历填充数据
     * @desc 带顺序的购物车列表，店铺顺序由旗下最新添加的商品顺序决定。展示给前端后，临界值看看是否操作，不需要的话
     *      他可以直接拿去展示。
     * @param userId 用户ID
     * @param skuIds
     * @return
     */
    @Override
    public List<ShopCartDTO> shopCarts(Long userId, List<Long> skuIds) {
        List<ShopCartSkuDTO> shopCartSkuDTOs = skuIds.stream()
                .map(skuId -> {
                    Integer count = (Integer) hashOperations.get(ShopCartConstant.getCartKey(userId), skuId);
                    return skuService.getShopCartSkuById(skuId)
                            .setCount(count);
                })
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

    /**
     * @CacheEvict allEntries = true: 清除当前`cacheNames`下的所有缓存
     * @param userId 用户ID
     * @param skuIds skuID数组（该参数必须是可变参数或者数组，后面需要转为 byte[][] 类型，如果
     */
    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = ShopCartConstant.SORT_SHOP_CART, key = "#userId", beforeInvocation = true),
            @CacheEvict(cacheNames = ShopCartConstant.SHOP_CART_PAGE, beforeInvocation = true, allEntries = true)
    })
    public void remove(Long userId, Long... skuIds) {
        hashOperations.delete(ShopCartConstant.getCartKey(userId), skuIds);
        hashOperations.delete(ShopCartConstant.getGroupingKey(userId), skuIds);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = ShopCartConstant.SORT_SHOP_CART, key = "#userId", beforeInvocation = true),
            @CacheEvict(cacheNames = ShopCartConstant.SHOP_CART_PAGE, beforeInvocation = true, allEntries = true)
    })
    public void clear(Long userId) {
        redisTemplate.delete(ShopCartConstant.getCartKey(userId));
        redisTemplate.delete(ShopCartConstant.getGroupingKey(userId));
    }

    @Override
    public Integer into(Long userId) {
        Long size = hashOperations.size(ShopCartConstant.getGroupingKey(userId));
        return getTotalPage(size.intValue());
    }

    /**
     * 获取购物车总页数
     * @param totalSize
     * @return
     */
    private Integer getTotalPage(Integer totalSize) {
        return totalSize % ShopCartConstant.PAGE_SIZE == 0 ?
                totalSize / ShopCartConstant.PAGE_SIZE :
                totalSize / ShopCartConstant.PAGE_SIZE + 1;
    }

    /**
     * `加入购物车`和`移出购物车`都检验sku是否`上架`
     * @param shopCartSkuDTO
     */
    private void isNormal(ShopCartSkuDTO shopCartSkuDTO) {
        if (!StatusEnums.NORMAL.getCode().equals(shopCartSkuDTO.getStatus())) {
            throw new ValidateException(ResponseEnum.SKU_IS_OFF_THE_SHELVES);
        }
    }

    private IShopCartService proxy() {
        return (ShopCartServiceImpl) AopContext.currentProxy();
    }

    private Long toLong(Object id) {
        return id instanceof  Long?
                (Long) id :
                Long.valueOf(((Integer) id).longValue());
    }

}
