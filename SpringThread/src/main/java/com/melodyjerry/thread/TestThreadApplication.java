package com.melodyjerry.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @classname TestThreadApplication
 * @description 测试异步任务
 */
@SpringBootApplication
public class TestThreadApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestThreadApplication.class, args);

        AsyncTaskService asyncTaskService = context.getBean(AsyncTaskService.class);
        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        System.out.println("This Program has Begun successfully");

    }
}
