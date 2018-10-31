package com.fatal.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步配置类
 * 配置异常线程池，异步异常处理类
 * 当我们使用@Async时，SpringBoot如果判断出容器中有`AsyncConfigurer`类型的配置组件,
 * 它就会在该组件中是否有`Executor`类型的线程池组件，如果没有，则会报错。
 * @author: Fatal
 * @date: 2018/10/31 0031 11:22
 */
@Slf4j
@EnableAsync    // 开启异步功能
@Configuration
public class MyAsyncConfigurer implements AsyncConfigurer {

    @Bean /** 创建一个该类型的组件放到Spring容器中 */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /** 核心线程数10：线程池创建时候初始化的线程数 */
        executor.setCorePoolSize(10);
        /** 最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程 */
        executor.setMaxPoolSize(20);
        /** 用来缓冲执行任务的队列 */
        executor.setQueueCapacity(200);
        /** 允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁 */
        executor.setKeepAliveSeconds(60);
        /** 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池 */
        executor.setThreadNamePrefix("taskExecutor-");
        /**
         *  线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，
         *  该策略会直接在 execute 方法调用的线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new MyAsyncExceptionHandler();
    }

    /**
     * 自定义异步方法返回类型为void的异常处理类
     * @Desc: 返回值为void的异步方法出现异常，就会被这个处理类捕获
     */
    class MyAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            log.info("Exception message - " + throwable.getMessage());
            log.info("Method name - " + method.getName());
            for (Object param : objects) {
                log.info("Parameter value - " + param);
            }
        }

    }
}
