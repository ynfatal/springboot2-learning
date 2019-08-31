package com.fatal.service.impl;

import com.fatal.annotation.Lock;
import com.fatal.service.IBusinessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 业务服务实现
 * @author Fatal
 * @date 2019/8/24 0024 20:24
 */
@Slf4j
@Service
public class BusinessServiceImpl implements IBusinessService {

    // 设置库存为10，AtomicInteger的所有方法都具有原子性
    private AtomicInteger stock = new AtomicInteger(10);

    @Override
    public void businessWithoutLock() {
        if (stock.get() > 0) {
            try {
                // 1ms 秒执行业务逻辑。当然这是假设，1ms肯定执行不完的。以此为例子，就算是业务很快完成，也会出现超卖
                Thread.sleep(1);
                stock.decrementAndGet();
                log.info("{}: 成功购买", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("{}: 抱歉，库存不足", Thread.currentThread().getName());
        }
    }

    @Override
    @Lock
    public void businessWithLock() {
        businessWithoutLock();
    }

    @Override
    public AtomicInteger getStock() {
        return this.stock;
    }

    @Override
    public Integer supplyStock(Integer increment) {
        return this.stock.addAndGet(increment);
    }

}
