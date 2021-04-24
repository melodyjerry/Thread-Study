import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * @version 1.0
 * @classname T01_WhatIsThread
 * @description TODO
 * @date 2021/4/24 14:31
 * @created by MelodyJerry
 */
public class T01_WhatIsThread {
    private static class Thread_1 extends Thread {
        @Override
        public void run() {
//            super.run();
            for(int i=0; i<10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程Thread_1...");
            }
        }
    }

    public static void main(String[] args) {
//        new MyThread_1().run(); //注意，两种方式的结果是不一样的，run()后是先执先执行完Thread_1，再打印10个main
        new Thread_1().start(); //要用start()启动线程
        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程main...");
        }
    }

}