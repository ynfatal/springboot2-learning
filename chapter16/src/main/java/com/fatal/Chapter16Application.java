package com.fatal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // 启用缓存
public class Chapter16Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter16Application.class, args);
    }
}
