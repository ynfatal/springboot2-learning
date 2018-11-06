package com.fatal.dao.order;

import com.fatal.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Order 数据库访问组件
 * @author: Fatal
 * @date: 2018/11/2 0002 10:13
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
