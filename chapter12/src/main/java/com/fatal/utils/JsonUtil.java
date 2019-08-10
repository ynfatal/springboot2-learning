package com.fatal.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Fatal
 * @date 2019/8/9 0009 22:47
 */
public class JsonUtil {

    /**
     * 控制台输出Json格式的对象
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

}
