package com.petrichor.java.language.concurrent.synchronize;

/**
 * synchronized的重入
 * 同一把锁，被A线程拿到，那么这个锁对象里所有用这边锁的地方对A都是开放的，对别的线程都是封闭的。
 */
public class SyncReentry1 {

    private synchronized void method1() {
        System.out.println("method1..");
        method2();
    }

    private synchronized void method2() {
        System.out.println("method2..");
        method3();
    }

    private synchronized void method3() {
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        new Thread(new SyncReentry1()::method1).start();
    }
}
