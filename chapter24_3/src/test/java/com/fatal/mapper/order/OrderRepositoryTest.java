package com.fatal.mapper.order;

import com.fatal.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Fatal
 * @date: 2018/11/2 0002 10:15
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    /**
     * 测试整合 spring data jpa
     */
    @Test
    public void save() {
        Order order = new Order().setOrderId("ORDER_123456")
                .setUserId("USER_123456")
                .setAmount(100000l)
                .setPhone("137504123456");
        Order save = repository.save(order);
        log.info("新增订单成功[order = {}]", save);
    }

}