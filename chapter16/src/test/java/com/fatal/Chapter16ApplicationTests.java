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
         * 测试添加
         */
        User user = new User().setId(1L).setUsername("fatal").setPassword("21");
        User result = userService.insertOrUpdate(user);
        log.info("【添加成功】 = [{}]", result);

        /**
         * 测试查询（有缓存，没有显示进入【selectById】方法）
         */
        User selectAfterInsert = userService.selectById(result.getId());
        log.info("【查询成功】 = [{}]", selectAfterInsert);

        /**
         * 测试删除（缓存也一同被删除了）
         */
        User remove = userService.remove(result.getId());
        log.info("【删除成功】 = [{}]", remove);

        /**
         * 测试删除后查询（缓存没了，显示进入【selectById】方法）
         */
        User selectAfterDelete = userService.selectById(result.getId());
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
        User selectAfterInsert = userService.selectById(1L);
        log.info("【查询成功】 = [{}]", selectAfterInsert);
    }

    @Test
    public void testRemove() {
        User remove = userService.remove(1L);
        log.info("【删除成功】 = [{}]", remove);
    }

    /**
     * 测试缓存管理器
     */
    @Test
    public void testCacheManager() {
        System.out.println(cacheManager);
    }

}
