package com.fatal.service.impl;

import com.fatal.dto.GoodsDTO;
import com.fatal.entity.Goods;
import com.fatal.mapper.GoodsMapper;
import com.fatal.service.IGoodsService;
import com.fatal.service.IHotSearchService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Fatal
 * @date 2019/8/22 0022 9:20
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    private GoodsMapper goodsMapper;
    private IHotSearchService hotSearchService;

    public GoodsServiceImpl(GoodsMapper goodsMapper, IHotSearchService hotSearchService) {
        this.goodsMapper = goodsMapper;
        this.hotSearchService = hotSearchService;
    }

    @Override
    public GoodsDTO getDetails(Long id) {
        Goods goods = Optional.ofNullable(goodsMapper.selectById(id))
                .orElseThrow(RuntimeException::new);
        hotSearchService.increment(goods.getId());
        return GoodsDTO.of(goods);
    }

    @Override
    public List<Long> getIds() {
        List<Goods> goods = goodsMapper.selectList(null);
        return CollectionUtils.isEmpty(goods) ?
                new ArrayList<>() : goods.stream()
                    .map(Goods::getId)
                    .collect(Collectors.toList());
    }

}
