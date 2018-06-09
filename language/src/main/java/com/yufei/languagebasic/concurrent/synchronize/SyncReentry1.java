package com.yufei.languagebasic.concurrent.synchronize;

/**
 * synchronized的重入
 *
 * @author alienware
 */
public class SyncReentry1 {

    public synchronized void method1() {
        System.out.println("method1..");
        method2();
    }

    public synchronized void method2() {
        System.out.println("method2..");
        method3();
    }

    public synchronized void method3() {
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final SyncReentry1 sd = new SyncReentry1();
        Thread t1 = new Thread(() -> sd.method1());
        t1.start();
    }
}
