package com.fatal.hodler;

/**
 * 模拟用户持有者
 * @author: Fatal
 * @date: 2018/11/8 0008 11:25
 */
public class CurrentUserHolder {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String get() {
        return holder.get() == null ? "unknown" : holder.get();
    }

    public static void set(String user) {
        holder.set(user);
    }

}
