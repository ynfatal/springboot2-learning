package com.fatal.service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 业务服务
 * @author Fatal
 * @date 2019/8/24 0024 20:22
 */
public interface IBusinessService {

    /**
     * 无锁业务
     * @param stock 库存
     */
    void businessWithoutLock(AtomicInteger stock);

    /**
     * 带锁业务
     * @param stock 库存
     */
    void businessWithLock(AtomicInteger stock);

}
