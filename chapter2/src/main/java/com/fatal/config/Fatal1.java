package com.fatal.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置类
 * @author: Fatal
 * @date: 2018/9/20 0020 16:16
 */
@Component
@ToString
@Data
public class Fatal1 {

    @Value("${fatal.name}")
    private String name;

    @Value("${fatal.age}")
    private Integer age;

}
