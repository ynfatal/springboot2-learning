package com.fatal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Chapter241Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter241Application.class, args);
    }
}
