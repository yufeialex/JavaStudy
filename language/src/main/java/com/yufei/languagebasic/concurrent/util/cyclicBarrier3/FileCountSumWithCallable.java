package com.yufei.languagebasic.concurrent.util.cyclicBarrier3;

/*
  Created by XinYufei on 2018/1/12.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用ExecutorService的invokeAll方法计算
 * <p>
 * 与CountListIntegerSum相比，这个不用自己控制等待，invokeAll执行给定的任务，当所有任务完成时，返回保持任务状态和结果的 Future 列表。
 * CountListIntegerSum用了同步，性能不好。这个去掉了同步，根据返回结果的 Future 列表相加就得到总和了。
 *
 * @author 飞雪无情
 */
public class FileCountSumWithCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        int threadCounts = 10 * Runtime.getRuntime().availableProcessors();//使用的线程数
        long sum = 0;
        ExecutorService exec = Executors.newFixedThreadPool(threadCounts);
        List<Callable<Long>> callList = new ArrayList<>();
        File file = new File("D:\\java学习");
        File[] files = file.listFiles();
        List<File> list = new ArrayList<>();
        for (File f : files) {
            list.add(f);
        }
        int len = list.size() / threadCounts;//平均分割List
        //List中的数量没有线程数多（很少存在）
        if (len == 0) {
            threadCounts = list.size();//采用一个线程处理List中的一个元素
            len = list.size() / threadCounts;//重新平均分割List
        }
        for (int i = 0; i < threadCounts; i++) {
            final List<File> subList;
            if (i == threadCounts - 1) {
                subList = list.subList(i * len, list.size());
            } else {
                subList = list.subList(i * len, len * (i + 1) > list.size() ? list.size() : len * (i + 1));
            }
            //采用匿名内部类实现
            callList.add(() -> {
                long subSum = 0L;
                for (File f1 : subList) {
                    BufferedReader reader = new BufferedReader(new FileReader(f1));
                    String s = reader.readLine();
                    Integer i1 = Integer.valueOf(s);
                    subSum += i1;
                    reader.close();
                }
                System.out.println("分配给线程：" + Thread.currentThread().getName() + "那一部分List的整数和为：\tSubSum:" + subSum);
                return subSum;
            });
        }
        List<Future<Long>> futureList = exec.invokeAll(callList);
        for (Future<Long> future : futureList) {
            sum += future.get();
        }
        exec.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("耗时： " + (end - start) / 1.0e3);
        System.out.println(sum);
    }

}