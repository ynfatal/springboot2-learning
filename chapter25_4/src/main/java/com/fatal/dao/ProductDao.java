package com.fatal.dao;

import com.fatal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Product 数据库访问层
 * @author: Fatal
 * @date: 2018/11/15 0015 10:55
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
