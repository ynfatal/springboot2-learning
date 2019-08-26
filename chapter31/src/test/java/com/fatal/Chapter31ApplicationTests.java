package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter31ApplicationTests {

    /**
     * TODO 商品超卖问题：在库存快不足时，如果这是用户多个下单导致扣库存产生并发的时候，就会出现超卖问题。
     *  这个时候，我们可以在扣库存的操作上加上锁，把扣库存变为原子操作，并发执行到扣库存这里需要排队，能拿到锁
     *  的线程，才能扣库存。这篇笔记的`Redis分布式锁`可以用于商品超卖问题，但是目前有个瓶颈，就是只能用于
     *  普通并发量不高的买卖场景。后边需要解决的问题如下：
     * 问题1：并发数如果过高，多个线程访问Redis，会出现连接超时
     * 问题2：单节点的情况下，配置数据怎么和Redisson的对上
     * 问题3：集群，之类的操作姿势
     */
    @Test
    public void contextLoads() {
    }

}
