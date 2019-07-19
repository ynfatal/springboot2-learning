package com.fatal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// 开启定时任务
@EnableScheduling
@SpringBootApplication
public class Chapter195Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter195Application.class, args);
    }

}
