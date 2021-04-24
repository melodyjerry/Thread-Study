package com.melodyjerry.thread;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

/**
 * @classname AsyncConfig
 * @description 开启异步执行的配置类
 */
@Configuration
@EnableAsync //开启异步执行
@ComponentScan("com.melodyjerry.thread")
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //线程池中的线程的名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix("SpringBoot线程池的前缀-");
        //线程池的核心线程数大小
        threadPoolTaskExecutor.setCorePoolSize(4);
        //线程池的最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(8);
        //等待队列的大小
        threadPoolTaskExecutor.setQueueCapacity(25);
        //执行初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
