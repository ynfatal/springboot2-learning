package com.fatal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fatal.mapper")
public class Chapter30Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter30Application.class, args);
    }

}
