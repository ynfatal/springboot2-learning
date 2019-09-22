package com.fatal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 2.1.6 开始不需要自己加 @EnableElasticsearchRepositories
 * springboot 已经在 ElasticsearchRepositoriesRegistrar 中帮我们加上了
 */
@SpringBootApplication
public class Chapter32Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter32Application.class, args);
    }

}
