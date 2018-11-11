package com.fatal.serivce.impl;

import com.fatal.entity.Product;
import com.fatal.security.AdminOnly;
import com.fatal.serivce.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Product 服务
 * @author: Fatal
 * @date: 2018/11/8 0008 11:16
 */
@Slf4j
@Service
public class ProductServiceImpl implements IProductService {

    @Override
    @AdminOnly // 加上该注解后的方法会走切面逻辑
    public void insert(Product product) {
        log.info("添加产品成功 -- [{}]", product);
    }

    @Override
    @AdminOnly
    public void delete(Long id) {
        log.info("删除id为[{}]的产品成功", id);
    }

}
