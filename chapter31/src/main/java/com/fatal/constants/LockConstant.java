package com.fatal.constants;

/**
 * @author Fatal
 * @date 2019/8/31 0031 12:23
 */
public interface LockConstant {

    /**
     * 锁前缀
     */
    String LOCK_PREFIX = "redisson_fair_lock:%s";

    /**
     * 格式化
     * @param lockName
     * @return
     */
    static String format(String lockName) {
        return String.format(LockConstant.LOCK_PREFIX, lockName);
    }

}
