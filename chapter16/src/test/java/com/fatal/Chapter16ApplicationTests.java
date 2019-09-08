package com.fatal;

import com.fatal.dto.ParamDTO;
import com.fatal.entity.User;
import com.fatal.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Chapter16ApplicationTests {

    @Autowired
    private IUserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCache() {
        /**
         * 测试查询（有缓存，没有显示进入【selectById】方法）
         */
        User search = userService.selectById(1L);
        log.info("【查询成功】 = [{}]", search);

        /**
         * 测试更新
         */
        search.setUsername("fatal")
                .setUpdateTime(LocalDateTime.now());
        User update = userService.update(search);
        log.info("【更新成功】 = [{}]", update);

        /**
         * 测试删除（缓存也一同被删除了）
         */
        User remove = userService.remove(search.getId());
        log.info("【删除成功】 = [{}]", remove);

        /**
         * 测试删除后查询（缓存没了，显示进入【selectById】方法）
         */
        User selectAfterDelete = userService.selectById(search.getId());
        log.info("【查询成功】 = [{}]", selectAfterDelete);
    }

    @Test
    public void testListUser() {
        List<User> users = userService.listUser();
        log.info("【查询成功】 = [{}]", users);
    }

    @Test
    public void testListUserWithParam() {
        ParamDTO paramDTO = new ParamDTO()
                .setId(1L)
                .setUsername("米彩");
        /*ParamDTO paramDTO = new ParamDTO()
                .setId(2L)
                .setUsername("米琪");*/
        List<User> users = userService.listUser(paramDTO);
        log.info("【查询成功】 = [{}]", users);
    }

    @Test
    public void testSelectById() {
        User search = userService.selectById(1L);
        log.info("【查询成功】 = [{}]", search);
    }

    @Test
    public void testRemove() {
        User remove = userService.remove(1L);
        log.info("【删除成功】 = [{}]", remove);
    }


    /**
     * 测试低流量情况下更新数据缓存与数据库双写一致性问题
     */
    @Test
    public void testLowFlowRateWithUpdate() {
        User search = userService.selectById(1L);
        search.setPassword("123456");
        userService.lowFlowRateWithUpdate(search);
        log.info("【更新成功】 = [{}]", search);
    }

    /**
     * 测试高并发情况下更新数据缓存与数据库双写一致性问题
     */
    @Test
    public void testHighConcurrencyWithUpdate() {
        User search = userService.selectById(1L);
        search.setPassword("123456");
        userService.highConcurrencyWithUpdate(search);
        log.info("【更新成功】 = [{}]", search);
    }

    /**
     * 测试缓存管理器
     */
    @Test
    public void testCacheManager() {
        System.out.println(cacheManager);
    }

}
