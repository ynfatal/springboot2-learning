package com.fatal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * 数据库访问层
 * @author: Fatal
 * @date: 2018/10/9 0009 20:32
 */
@Repository
public class RedisDao {

    /**
     * Redis的String数据结构 （推荐使用StringRedisTemplate）
     */
    @Autowired
    private StringRedisTemplate template;

    public void setKey(String key, String value) {
        // 获得String类型操作对象
        ValueOperations<String, String> ops = template.opsForValue();
        // 第三个参数表示数量为1；第四个参数`TimeUnit.MINUTES`单位为分钟
        ops.set(key, value, 1, TimeUnit.MINUTES); //1分钟过期
    }

    public String getValue(String key) {
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }

}
