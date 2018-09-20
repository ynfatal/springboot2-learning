package com.fatal.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

/**
 * 配置类
 * @author: Fatal
 * @date: 2018/9/20 0020 16:16
 */
@Component
@ConfigurationProperties(prefix = "test")
@ToString
@Data
public class Fatal5 {

    private String name;

    private Integer age;

}
