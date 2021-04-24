package com.melodyjerry.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @classname AsyncTaskService
 * @description 异步任务的执行类
 */
@Service
public class AsyncTaskService {
    @Async //异步方法
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务: "+i);
    }

    @Async //异步方法
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1： " + (i+1));
    }
}
