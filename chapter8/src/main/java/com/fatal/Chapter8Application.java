package com.fatal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fatal.mapper")  // 扫描指定包下的所有mapper接口
public class Chapter8Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter8Application.class, args);
    }
}
