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
@ConfigurationProperties(prefix = "fatal")
@ToString
@Data
@Validated     // 开启校验
public class Fatal3 {

    private String name;

    @Min(20)
    private Integer age;

    @Email
    private String email;
}
