package com.fatal.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源配置类
 *
 * @author: Fatal
 * @date: 2018/10/3 0003 21:49
 */
@Configuration
public class DataSourceConfig {

    /**
     * 配置数据库
     * @desc @ConfigurationProperties 标注在方法上，会将配置数据映射到要`return`出来的对象中
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

}
