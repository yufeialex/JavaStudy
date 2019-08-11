/*
  Meituan.com Inc.
  Copyright (c) 2010-2018 All Rights Reserved.
 */
package com.yufei.languagebasic.concurrent.timeout;

/**
 * <p></p>
 *
 * @author
 * @version $Id: AA.java, v 0.1 2018-06-06 下午12:47 @xinyufei $$
 */
public class JoinTest {
    public static void main(String[] args) {
        Task task1 = new Task("one", 4);
        Task task2 = new Task("two", 2);
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        try {
            t1.join(2000); // 在主线程中等待t1执行2秒
        } catch (InterruptedException e) {
            System.out.println("t1 interrupted when waiting join");
            e.printStackTrace();
        }
        t1.interrupt(); // 这里很重要，一定要打断t1,因为它已经执行了2秒。
        t2.start();
        try {
            t2.join(1000);
        } catch (InterruptedException e) {
            System.out.println("t2 interrupted when waiting join");
            e.printStackTrace();
        }
    }

}

class Task implements Runnable {
    private String name;
    private int time;

    public Task(String s, int t) {
        name = s;
        time = t;
    }

    public void run() {
        for (int i = 0; i < time; ++i) {
            System.out.println("task " + name + " " + (i + 1) + " round");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(name + " is interrupted when calculating, will stop...");
                return; // 注意这里如果不return的话，线程还会继续执行，所以任务超时后在这里处理结果然后返回
            }
        }
    }
}