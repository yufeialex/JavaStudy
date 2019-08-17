package com.petrichor.java.language.concurrent.util.cyclicBarrier3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 计算List中所有整数的和测试类
 *
 * @author 飞雪无情
 * @since 2010-7-12
 */
public class FileNoConcurrentSumMain {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        File file = new File("D:\\java学习");
        File[] files = file.listFiles();
        List<File> files1 = Arrays.asList(files);

        long noCurrentSum = 0L;
        for (File f : files1) {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String s = reader.readLine();
            Integer i1 = Integer.valueOf(s);
            noCurrentSum += i1;
            reader.close();
        }

        long end = System.currentTimeMillis();

        System.out.println("List中所有整数的和为:" + noCurrentSum);
        System.out.println("所用时间:" + (end - start) / 1.0e3);
    }

}