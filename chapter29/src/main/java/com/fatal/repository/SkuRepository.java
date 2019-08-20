package com.fatal.repository;

import com.fatal.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Fatal
 * @date 2019/8/14 0014 17:33
 */
public interface SkuRepository extends JpaRepository<Sku, Long> {

    /**
     * 查找未删除的Sku
     * @param id
     * @param status
     * @return
     */
    Sku findByIdAndStatusIsNot(Long id, Integer status);

}
