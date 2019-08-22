package com.fatal.service;

import com.fatal.dto.GoodsDTO;

import java.util.List;

/**
 * 商品服务
 * @author Fatal
 * @date 2019/8/22 0022 9:19
 */
public interface IGoodsService {

    /**
     * 查看商品详情
     * @param id
     * @return
     */
    GoodsDTO getDetails(Long id);

    /**
     * 获取所有的ID（方便做测试数据）
     * @return
     */
    List<Long> getIds();

}
