package com.fatal.service;

import com.fatal.dto.ShopCartDTO;
import com.fatal.dto.ShopCartMainDTO;

import java.util.List;

/**
 * 购物车服务
 * @author Fatal
 * @date 2019/8/14 0014 23:20
 */
public interface IShopCartService {

    /**
     * 将指定数量的商品加入购物车
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @param increment 增量
     */
    void increment(Long userId, Long goodsId, Long increment);

    /**
     * 移出购物车，移出数量为 1
     * @param userId 用户ID
     * @param goodsId 商品ID
     */
    void removeOne(Long userId, Long goodsId);

    /**
     * 购物车列表
     * @param userId 用户ID
     * @param goodsIds 10个id（这个由前端控制，淘宝购物车就是没下拉一次加载`10`个）
     * @return
     */
    List<ShopCartDTO> shopCarts(Long userId, List<Long> goodsIds);

    /**
     * 移除购物车指定商品
     * @param userId 用户ID
     * @param goodsIds 商品ID数组（该参数必须是可变参数或者数组，后面需要转为 byte[][] 类型，如果
     *                 这里用集合的话，后面序列化的 byte[][] 结果是错的）
     */
    void remove(Long userId, Long... goodsIds);

    /**
     * 清空购物车
     * @param userId 用户ID
     */
    void clear(Long userId);

    /**
     * 进入购物车
     * @param userId 用户ID
     * @return Map<Object, Object> => Map<goodsId, count> => List<ShopCartMainDTO>
     *     这里为什么用Object? 因为 spring data redis 是根据数值的大小来决定接收的类型是 Integer 还是 Long。
     *     所以这边的 key 和 value 的类型不能确定为某一类型。
     */
    List<ShopCartMainDTO> into(Long userId);

}
