package com.yufei.languagebasic.concurrent.util.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 资源可以被并行访问，但是访问数量有限制的时候
 */
public class UseSemaphore {

    public static void main(String[] args) throws InterruptedException {
        // 线程池  
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问  
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问  
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            exec.execute(() -> {
                try {
                    // 获取许可
                    semp.acquire();
                    System.out.println("Accessing: " + NO);
                    //模拟实际业务逻辑
                    Thread.sleep((long) (Math.random() * 10000));
                    // 访问完后，释放
                    semp.release();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            });
        }
        Thread.sleep(10);
        System.out.println(semp.getQueueLength());

        // 退出线程池  
        exec.shutdown();
    }

}  