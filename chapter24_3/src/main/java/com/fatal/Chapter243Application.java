package com.fatal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAspectJAutoProxy(exposeProxy = true) // 开启对AspectJ自动代理，并暴露代理组件
@EnableAsync //开启异步功能
@SpringBootApplication
public class Chapter243Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter243Application.class, args);
    }
}
