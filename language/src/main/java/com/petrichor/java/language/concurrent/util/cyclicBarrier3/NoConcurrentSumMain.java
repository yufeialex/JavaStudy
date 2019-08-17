package com.petrichor.java.language.concurrent.util.cyclicBarrier3;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算List中所有整数的和测试类
 *
 * @author 飞雪无情
 * @since 2010-7-12
 */
public class NoConcurrentSumMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        //生成的List数据
        for (int i = 1; i <= 200; i++) {
            list.add(i);
        }

        long noCurrentSum = 0L;
        for (Integer i : list) {
            noCurrentSum += i;
        }

        long end = System.currentTimeMillis();

        System.out.println("List中所有整数的和为:" + noCurrentSum);
        System.out.println("所用时间:" + (end - start) / 1.0e3);
    }

}