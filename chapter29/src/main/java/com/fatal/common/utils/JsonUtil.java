package com.fatal.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 单例模式
 * @author Fatal
 * @date 2019/8/9 0009 22:47
 */
public class JsonUtil {

    private static Gson gson;

    static {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            gson = gsonBuilder.create();
        }
    }

    private JsonUtil() {}

    /**
     * 控制台输出Json格式的对象
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

}
