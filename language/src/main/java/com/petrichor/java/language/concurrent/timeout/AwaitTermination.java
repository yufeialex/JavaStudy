/*
  Meituan.com Inc.
  Copyright (c) 2010-2018 All Rights Reserved.
 */
package com.petrichor.java.language.concurrent.timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author
 * @version $Id: AA.java, v 0.1 2018-06-06 下午1:59 @xinyufei $$
 */
public class AwaitTermination {
    static class Task implements Runnable {
        String name;
        private int time;

        Task(String s, int t) {
            name = s;
            time = t;
        }

        public void run() {
            for (int i = 0; i < time; ++i) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(name + " is interrupted when calculating, will stop...");
                    return; // 注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
                }
                System.out.println("task " + name + " " + (i + 1) + " round");
            }
            System.out.println("task " + name + " finished successfully");
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task("one", 5);
        Task task2 = new Task("two", 2);
        Future<?> future = executor.submit(task);
        Future<?> future2 = executor.submit(task2);
        List<Future<?>> futures = new ArrayList<Future<?>>();
        futures.add(future);
        futures.add(future2);
        try {
            if (executor.awaitTermination(3, TimeUnit.SECONDS)) {
                System.out.println("task finished");
            } else {
                System.out.println("task time out,will terminate");
                for (Future<?> f : futures) {
                    if (!f.isDone()) {
                        f.cancel(true);
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("executor is interrupted");
        } finally {
            executor.shutdown();
        }
    }
}
