package com.fatal.service.impl;

import com.fatal.common.constants.HotSearchConstant;
import com.fatal.service.IHotSearchService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 热搜服务实现
 * @author Fatal
 * @date 2019/8/22 0022 9:55
 */
@Service
public class HotSearchServiceImpl implements IHotSearchService {

    private ZSetOperations<String, Serializable> zSetOperations;

    public HotSearchServiceImpl(RedisTemplate<String, Serializable> redisTemplate) {
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    @Override
    public void increment(Long goodsId) {
        zSetOperations.incrementScore(HotSearchConstant.HOT_SEARCH, goodsId, 1);
    }

    /**
     * @desc reverseRangeByScore(..)底层用LinkedHashSet封装，所以是有序的。
     *      对应 Redis 命令: zrevrangebyscore key max min [WITHSCORES] [LIMIT offset count]
     * @return
     */
    @Override
    public List<Long> hotSearchList() {
        Set<Serializable> idSet = zSetOperations.reverseRangeByScore(HotSearchConstant.HOT_SEARCH,
                0, Double.MAX_VALUE, 0, HotSearchConstant.TOP_NUMBER);
        if (!CollectionUtils.isEmpty(idSet)) {
            List<Long> ids = idSet.stream()
                    .map(this::toLong)
                    .collect(Collectors.toList());
            return ids;
        }
        return new ArrayList<>();
    }

    /**
     * @desc reverseRangeByScoreWithScores(..)底层用LinkedHashSet封装，所以是有序的。
     *      对应 Redis 命令: zrevrangebyscore key max min [WITHSCORES] [LIMIT offset count]
     * @return
     */
    @Override
    public Map<Serializable, Double> hotSearchWithScoreList() {
        Set<ZSetOperations.TypedTuple<Serializable>> typedTuples =
                zSetOperations.reverseRangeByScoreWithScores(HotSearchConstant.HOT_SEARCH,
                        0, Double.MAX_VALUE, 0 , HotSearchConstant.TOP_NUMBER);
        if (CollectionUtils.isEmpty(typedTuples)) {
            return new LinkedHashMap<>(16);
        }
        return typedTuples.stream()
                .collect(
                        Collectors.toMap(
                                ZSetOperations.TypedTuple::getValue,
                                ZSetOperations.TypedTuple::getScore,
                                // key 重复时执行的方法
                                (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u));},
                                LinkedHashMap::new
                        )
                );
    }

    private Long toLong(Serializable id) {
        return id instanceof Long ?
                (Long) id :
                Long.valueOf(((Integer) id).longValue());
    }
}
