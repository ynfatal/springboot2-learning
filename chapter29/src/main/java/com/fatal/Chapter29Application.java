package com.fatal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching // 启用缓存
@SpringBootApplication
public class Chapter29Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter29Application.class, args);
    }

}
