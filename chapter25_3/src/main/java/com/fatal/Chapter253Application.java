package com.fatal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
// 强制使用cglib代理
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Chapter253Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter253Application.class, args);
    }
}
