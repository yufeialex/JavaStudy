package com.petrichor.languagebasic.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 在没有设定线程最大数1000之前， 程序可以创建到8000个线程。重新启动机器，执行 echo 1000 > /proc/sys/kernel/threads-max
 * 然后再运行，出现了创建进程错误的提示，线程数超过最大线程数。（但是要确保内存空间充足，否则先报栈空间溢出的错误。）
 * Created by XinYufei on 2018/1/11.
 */
public class MaxThreadsMain {
    private static final int BATCH_SIZE = 4000;

    public static void main(String... args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        try {
            for (int i = 0; i <= 100 * 1000; i += BATCH_SIZE) {
                long start = System.currentTimeMillis();
                addThread(threads, BATCH_SIZE);
                long end = System.currentTimeMillis();
                Thread.sleep(1000);
                long delay = end - start;
                System.out.printf("%,d threads: Time to create %,d threads was %.3f seconds %n", threads.size(), BATCH_SIZE, delay / 1e3);
            }
        } catch (Throwable e) {
            System.err.printf("After creating %,d threads, ", threads.size());
            e.printStackTrace();
        }

    }

    private static void addThread(List<Thread> threads, int num) {
        for (int i = 0; i < num; i++) {
            Thread t = new Thread(() -> {
                try {
                    while (!Thread.interrupted()) {
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ignored) {
                    //
                }
            });
            t.setDaemon(true);
            t.setPriority(Thread.MIN_PRIORITY);
            threads.add(t);
            t.start();
        }
    }
}
