package com.yufei.languagebasic.concurrent.other.conn008;

import java.util.ArrayList;
import java.util.List;

/**
 * wait notfiy 方法，wait释放锁，notfiy不释放锁
 *
 * @author alienware
 */
public class ListAdd2 {
    private volatile static List<String> list = new ArrayList<>();

    public void add() {
        list.add("bjsxt");
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {

        final ListAdd2 list2 = new ListAdd2();

        // 1 实例化出来一个 lock
        // 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
        final Object lock = new Object();

//		final CountDownLatch countDownLatch = new CountDownLatch(2); // 几次通知才执行

        Thread t1 = new Thread(() -> {
            try {
                synchronized (lock) {
                    for (int i = 0; i < 10; i++) {
                        list2.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                        Thread.sleep(500);
                        if (list2.size() == 5) {
                            System.out.println("已经发出通知..");
//                            countDownLatch.countDown();
                            lock.notify();
                        }
                        if (list2.size() == 8) {
                            System.out.println("再次发出通知..");
//                            countDownLatch.countDown();
                            lock.notify();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                if (list2.size() != 5) {
                    try {
                        System.out.println("t2进入...");
                        lock.wait();
//                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
//                throw new RuntimeException();
            }
        }, "t2");


        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                if (list2.size() != 5) {
                    try {
                        System.out.println("t3进入...");
                        lock.wait();
//                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
//                throw new RuntimeException();
            }
        }, "t3");

        t3.start();
        t2.start();
        // t2先执行的，然后遇到wait就释放锁了，被t1获得锁，但是t1调用notify之后，并不释放锁，
        // 自己执行完了之后才释放锁，然后t2才能继续执行
        Thread.sleep(1000);
        t1.start();

    }

}
