/*
  Meituan.com Inc.
  Copyright (c) 2010-2018 All Rights Reserved.
 */
package com.petrichor.java.language.concurrent.timeout;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author
 * @version $Id: AAA.java, v 0.1 2018-06-06 下午2:00 @xinyufei $$
 */
public class DemonThread {
    static class Task implements Runnable {
        private String name;
        private int time;

        Task(String s, int t) {
            name = s;
            time = t;
        }

        public int getTime() {
            return time;
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

    static class Daemon implements Runnable {
        List<Runnable> tasks = new ArrayList<Runnable>();
        private Thread thread;
        private int time;

        Daemon(Thread r, int t) {
            thread = r;
            time = t;
        }

        public void addTask(Runnable r) {
            tasks.add(r);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(time * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread.interrupt();
            }
        }

    }

    public static void main(String[] args) {
        Task task1 = new Task("one", 5);
        Thread t1 = new Thread(task1);
        Daemon daemon = new Daemon(t1, 3);
        Thread daemonThread = new Thread(daemon);
        daemonThread.setDaemon(true);
        t1.start();
        daemonThread.start();
    }
}
