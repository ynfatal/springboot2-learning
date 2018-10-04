package com.fatal.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
     */
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource getDataSource() {
        /**
         * @ConfigurationProperties 标注在方法上，会将配置数据映射到`new`出来的对象中
         */
        return new DruidDataSource();
    }

    /**
     * 开启事务
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getDataSourceTransactionManager(@Qualifier("dataSource") DataSource datasource) {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(datasource);
        return manager;
    }

}
