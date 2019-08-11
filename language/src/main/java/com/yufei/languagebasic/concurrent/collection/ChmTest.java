package com.yufei.languagebasic.concurrent.collection;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by XinYufei on 2018/1/19.
 */
public class ChmTest {
    private static ConcurrentHashMap publishProgress = new ConcurrentHashMap<String, Integer>();

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(es);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int bb = i;
            completionService.submit(() -> {
//                Thread.sleep(random.nextInt(1000));
                Thread.sleep(100);
                synchronized (ChmTest.class) { // 整体性操作，必须加锁，否则最后很可能到不了100
                    Integer currentNo = (Integer) publishProgress.getOrDefault("xyf", 0);
//                System.out.println("Thread number: " + bb);
                    publishProgress.put("xyf", currentNo + 1);
                }

                return 1;
            });
        }
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true) {
                Integer aa = (Integer) publishProgress.get("xyf");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(aa);
            }
        }).start();
        es.shutdown();
    }
}
