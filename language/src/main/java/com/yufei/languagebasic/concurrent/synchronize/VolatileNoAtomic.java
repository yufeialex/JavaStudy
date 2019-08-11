package com.yufei.languagebasic.concurrent.synchronize;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 *
 */
public class VolatileNoAtomic extends Thread {

    //其实就是volatile修饰的变量只能读同步，写不能同步
    private static volatile int count;

    //	private static AtomicInteger count = new AtomicInteger(0);
    private static void addCount() {
        for (int i = 0; i < 1000; i++) {
            count++;
//			count.incrementAndGet();
        }
        System.out.println(count);
    }

    public void run() {
        addCount();
    }

    public static void main(String[] args) {

        VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
        for (int i = 0; i < 10; i++) {
            arr[i] = new VolatileNoAtomic();
        }

        for (int i = 0; i < 10; i++) {
            arr[i].start();
        }
    }


}
