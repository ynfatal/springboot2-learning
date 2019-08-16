package com.fatal.repository;

import com.fatal.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Fatal
 * @date 2019/8/14 0014 17:33
 */
public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
