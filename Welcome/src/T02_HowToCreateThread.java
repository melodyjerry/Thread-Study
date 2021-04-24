import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @version 1.0
 * @classname T01_WhatIsThread.T02_HowToCreateThread
 * @description TODO
 * @date 2021/4/24 14:46
 * @created by MelodyJerry
 */
public class T02_HowToCreateThread {
    /**
     * 继承 Thread, 重写 run()
     */
    static class MyThread extends Thread {
        @Override
        public void run() {
//            super.run();
            System.out.println("Hello MyThread, 【extends Thread】 !");
        }
    }

    /**
     * 实现 Runnable, 重写run()
     */
    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun, 【implements Runnable】 !");
        }
    }

    /**
     * 实现 Callable, 重写call()
     */
    static class MyCallWithoutV implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println("Hello MyCallWithoutV, 【implements Callable】");
            return "success, implements Callable<String>";
        }
    }

    /**
     * 实现 Callable<返回值类型>, 重写call()
     */
    static class MyCallWithString implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("Hello MyCallWithString, 【implements Callable<返回值类型>】");
            return "success, implements Callable<String>";
        }
    }

    public static void main(String[] args) {
        /**
         * 第一种
         */
        new MyThread().start();

        /**
         * 第二种: 实现Runnable接口，重写run()方法
         */
        new Thread(new MyRun()).start();

        /**
         * 第三种: Lambda表达式
         */
        new Thread(() -> {
            System.out.println("Hello 【Lambda】 !");
        }).start();

        /**
         * 第四种: 匿名内部类
         */
        //方式1：相当于继承了Thread类，作为子类重写run()实现
        new Thread() {
            public void run() {
                System.out.println("【匿名内部类】创建线程方式1...");
            };
        }.start();
        //方式2:实现Runnable,Runnable作为匿名内部类
        new Thread(new Runnable() {
            public void run() {
                System.out.println("【匿名内部类】创建线程方式2...");
            }
        } ).start();

        /**
         * 第五种: FutureTask + Callable
         * implements Callable<返回值类型>, @Override call()
         */
        new Thread(new FutureTask<String>(new MyCallWithoutV())).start();
        new Thread(new FutureTask<>(new MyCallWithString())).start();

        /**
         * 第六种: 线程池的实现（有2种方式）
         * 2种方式返回的实际上是ExecutorService,而ExecutorService是Executor的子接口
         * 方式① Executors.newFixedThreadPool(线程数)
         * 方式② Executors.newCachedThreadPool()
         */
        //方式1 Executors.newFixedThreadPool(线程数)
        //创建带有5个线程的线程池
        ExecutorService threadPool_1 = Executors.newFixedThreadPool(5);
        for(int i = 0 ;i < 10 ; i++) {
            threadPool_1.execute(new Runnable() {
                public void run() {
                    System.out.println("【线程池实现】①Executors.newFixedThreadPool(线程数), 线程"+Thread.currentThread().getName()+" is running");
                }
            });
        }
        threadPool_1.shutdown(); //优雅的关闭线程池, 用shutdown()
        //方式2 Executors.newCachedThreadPool()
        ExecutorService threadPool_2 = Executors.newCachedThreadPool();
        threadPool_2.execute(() -> {
            System.out.println("【线程池实现】②Executors.newCachedThreadPool and Lambda !");
        });
        threadPool_2.shutdown(); //优雅的关闭线程池, 用shutdown()

        //第七种:定时器（这里使用Java自带的定时器，更好的是用quartz作业调度框架）
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("【定时器方式】定时任务延迟0(即立刻执行),每隔1000ms执行一次");
            }
        }, 0, 1000);
    }
}
