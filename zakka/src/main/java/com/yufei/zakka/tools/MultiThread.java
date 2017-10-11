package com.yufei.zakka.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThread {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 13000000; i++) {
            list.add("a" + i);
        }
        // StringBuilder sbBuilder = new StringBuilder();
        // for(String string : list) {
        // sbBuilder.append(string);
        // }

        // 多线程
        try {
            MultiThread.list2Str(list, 4);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("程序运行时间： " + (endTime - startTime) / 1000 + "s");
    }

    public static String list2Str(final List<String> list, final int nThreads)
            throws Exception {
        if (list == null || list.isEmpty()) {
            return null;
        }

        int len = 0;
        for (String str : list) {
            len += str.length();
        }
        StringBuffer ret = new StringBuffer();

        final int size = list.size();
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        List<Future<String>> futures = new ArrayList<Future<String>>(nThreads);

        try {
            for (int i = 0; i < nThreads; i++) {
                final int j = i;
                /*
				 * Callable<String> task = new Callable<String>() {
				 * 
				 * @Override 
				 * public String call() throws Exception {
				 * 
				 * int len = 0; for (int n = size / nThreads * j; n < size /
				 * nThreads * (j + 1); n++) { len += list.get(n).length(); }
				 * 
				 * StringBuffer sb = new StringBuffer(len); for (int n = size /
				 * nThreads * j; n < size / nThreads * (j + 1); n++) {
				 * sb.append(list.get(n)); } return sb.toString(); } };
				 */
                Callable<String> task = () -> {
                    int len1 = 0;
                    for (int n = size / nThreads * j; n < size / nThreads * (j + 1); n++) {
                        len1 += list.get(n).length();
                    }

                    StringBuffer sb = new StringBuffer(len1);
                    for (int n = size / nThreads * j; n < size / nThreads * (j + 1); n++) {
                        sb.append(list.get(n));
                    }
                    return sb.toString();
                };
                futures.add(executorService.submit(task));
            }

            for (Future<String> future : futures) {
                ret.append(future.get());
            }
        } finally {
            executorService.shutdown();
        }

        return ret.toString();
    }
}
