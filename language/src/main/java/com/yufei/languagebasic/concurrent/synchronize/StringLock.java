package com.yufei.languagebasic.concurrent.synchronize;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 *
 * 导致所有字面量一样的string是同一把锁，不能实现线程同步，反而造成同步阻塞；
 *
 */
public class StringLock {

    private static final String lock = "this is a lock";
    private static final String lock1 = "this is a lock";

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("locked ...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("unlocked ...");
            }
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println("locked lock1 ...");
                System.out.println("unlocked lock1 ...");
            }
        }).start();
    }
}
