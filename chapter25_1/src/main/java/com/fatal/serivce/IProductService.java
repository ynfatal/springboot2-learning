package com.fatal.serivce;

import com.fatal.entity.Product;

/**
 * @author: Fatal
 * @date: 2018/11/8 0008 11:15
 */
public interface IProductService {

    void insert(Product product);

    void delete(Long id);

}
