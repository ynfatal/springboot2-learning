package com.fatal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter15ApplicationTests {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ConcurrentMapCacheManager concurrentMapCacheManager;

    @Test
    public void contextLoads() {
        System.out.println(cacheManager);
        System.out.println(concurrentMapCacheManager);
    }

}
