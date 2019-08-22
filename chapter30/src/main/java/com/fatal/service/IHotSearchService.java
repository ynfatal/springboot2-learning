package com.fatal.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 热搜服务
 * @author Fatal
 * @date 2019/8/22 0022 9:55
 */
public interface IHotSearchService {

    /**
     * 指定商品的搜索次数加一
     * @param goodsId 商品ID
     */
    void increment(Long goodsId);

    /**
     * 获取热搜列表
     * @return
     */
    List<Long> hotSearchList();

    /**
     * 获取热搜列表（带score）
     * @return
     */
    Map<Serializable, Double> hotSearchWithScoreList();

}
