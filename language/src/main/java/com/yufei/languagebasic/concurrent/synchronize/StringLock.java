package com.yufei.languagebasic.concurrent.synchronize;

/**
 * synchronized代码块对字符串的锁，注意String常量池的缓存功能
 * 不能实现线程同步，反而造成同步阻塞；
 *
 * @author alienware
 */
public class StringLock {

    static final String lock = "this is a lock";
    static final String lock1 = "this is a lock";

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("locked ...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
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
