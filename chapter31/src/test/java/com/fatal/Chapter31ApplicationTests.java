package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter31ApplicationTests {

    /**
     * TODO 这个`Redis分布式锁`可以用于普通并发量不高的买卖场景。后边需要解决的问题如下：
     * 问题1：并发数如果过高，Redis会出现连接超时
     * 问题2：单节点的情况下，配置数据怎么和Redisson的对上
     * 问题3：集群，之类的操作姿势
     */
    @Test
    public void contextLoads() {
    }

}
