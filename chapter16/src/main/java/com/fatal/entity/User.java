package com.fatal.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/10/15 0015 11:23
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;

    /**
     * LocalDateTime 由于没有无参构造器，所以它需要使用特定的序列化器 `LocalDateTimeSerializer`
     * 和 反序列化 `LocalDateTimeDeserializer`
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

}
