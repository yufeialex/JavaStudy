package com.yufei.languagebasic.concurrent.util.cyclicBarrier3;

/*
  Created by XinYufei on 2018/1/12.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 用Java7中的Fork/Join框架计算 int数组之和
 * 《Java并发编程的艺术》Page177-178
 */
public class CountSumWithForkJoin {

    private static final int THREADCOUNT = Runtime.getRuntime().availableProcessors() + 1; //线程数
    private static final int NUM = 20000000 + 1; //要计算的list大小
    private static final int THRESHOLD = NUM / THREADCOUNT; //阈值
    private static List<Integer> list = new ArrayList<Integer>(NUM); //需要计算和的数组

    public static void main(String[] args) {
        // 初始化数组
        for (int i = 0; i < NUM; i++) {
            list.add(i);
        }
        long startTime = System.nanoTime();
        long serialSum = 0;
        for (int i = 0; i < NUM; i++) {
            serialSum += list.get(i);
        }
        long usedTime = System.nanoTime() - startTime;
        System.out.println("[单线程] Sum is : " + serialSum + ", time is : " + usedTime);

        startTime = System.nanoTime();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, NUM - 1);
        Future<Long> result = forkJoinPool.submit(task);
        try {
            long sum = result.get();
            usedTime = System.nanoTime() - startTime;
            System.out.println("[多线程] Sum is : " + sum + ", time is : " + usedTime);
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }

    static class CountTask extends RecursiveTask<Long> {
        private int start;
        private int end;

        CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0L;
            //如果任务小就计算任务
            boolean canCompute = (end - start) <= THRESHOLD;
            if (canCompute) {
                for (int i = start; i <= end; i++) {
                    sum += list.get(i); //如果用list.get操作，则非常耗时，在任何情况下，耗时都比单线程高
                }
            } else {
                //如果任务大于阈值，就分裂成两个子任务计算
                int middle = (start + end) / 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);
                //执行子任务
                leftTask.fork();
                rightTask.fork();
                //等待子任务执行完毕，并得到其结果
                long leftResult = leftTask.join();
                long rightResult = rightTask.join();
                sum = leftResult + rightResult;
                System.out.println(Thread.currentThread().getName() + ", start=" + start
                        + ", end=" + end + ", middle=" + middle + ", sum=" + sum);
            }
            return sum;
        }

    }

}
