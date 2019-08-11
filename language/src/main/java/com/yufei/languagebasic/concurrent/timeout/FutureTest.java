/*
  Meituan.com Inc.
  Copyright (c) 2010-2018 All Rights Reserved.
 */
package com.yufei.languagebasic.concurrent.timeout;

import java.util.concurrent.*;

/**
 * <p></p>
 *
 * @author
 * @version $Id: AA.java, v 0.1 2018-06-06 下午1:59 @xinyufei $$
 */
public class FutureTest {
    static class Task implements Callable<Boolean> {
        String name;
        private int time;

        Task(String s, int t) {
            name = s;
            time = t;
        }

        @Override
        public Boolean call() throws Exception {
            for (int i = 0; i < time; ++i) {
                System.out.println("task " + name + " round " + (i + 1));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(name + " is interrupted when calculating, will stop...");
                    return false; // 注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task1 = new Task("one", 5);
        Future<Boolean> f1 = executor.submit(task1);
        try {
            if (f1.get(2, TimeUnit.SECONDS)) { // future将在2秒之后取结果
                System.out.println("one complete successfully");
            }
        } catch (InterruptedException e) {
            System.out.println("future在睡着时被打断");
            executor.shutdownNow();
        } catch (ExecutionException e) {
            System.out.println("future在尝试取得任务结果时出错");
            executor.shutdownNow();
        } catch (TimeoutException e) {
            System.out.println("future时间超时");
            f1.cancel(true);
            // executor.shutdownNow();
            // executor.shutdown();
        } finally {
            executor.shutdownNow();
        }
    }
}
