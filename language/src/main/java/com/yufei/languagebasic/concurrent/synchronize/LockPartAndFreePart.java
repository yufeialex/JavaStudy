package com.yufei.languagebasic.concurrent.synchronize;

/**
 * 对象锁的同步和异步问题
 *
 */
public class LockPartAndFreePart {

    private synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
//			上锁部分一般都是有耗时操作的
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 没上锁，可以并行执行。且可以与method1并行执行
     */
    private void method2() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        final LockPartAndFreePart lp = new LockPartAndFreePart();

        /*
          分析：
          t1线程先持有object对象的Lock锁，t2线程可以以异步的方式调用对象中的非synchronized修饰的方法.
          t2线程如果在这个时候调用对象中的同步（synchronized）方法则需等待，也就是同步.
         */
        Thread t1 = new Thread(lp::method1, "t1");
        Thread t2 = new Thread(lp::method2, "t2");

        t1.start();
        t2.start();
    }

}
