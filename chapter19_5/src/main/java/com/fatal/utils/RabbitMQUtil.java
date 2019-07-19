package com.fatal.utils;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.GetResponse;

/**
 * RabbitMQ 工具类
 * @author: Fatal
 * @date: 2019/7/19 0019 11:08
 */
public class RabbitMQUtil {

    /**
     * Json 反序列化
     * @param getResponse
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static  <T> T parseObject(GetResponse getResponse, Class<T> clazz) {
        byte[] body = getResponse.getBody();
        return (T) JSONObject.parseObject(body, clazz);
    }

}
