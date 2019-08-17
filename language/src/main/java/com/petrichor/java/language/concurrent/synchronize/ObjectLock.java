package com.petrichor.java.language.concurrent.synchronize;

/**
 * 使用synchronized代码块加锁,比较灵活
 */
public class ObjectLock {
    // 如果类里有多个方法都需要加锁，但它们能彼此不互斥，可以并行被调用，就不可以在方法上加synchronize。因为这样用的都是this加锁。
    // 那么就用别的对象给它加锁，因为只要有对象，就是可以作为一个锁。

    private void method1() {
        synchronized (this) { // 对象锁
            try {
                System.out.println("do method1..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void method2() { // 类锁
        synchronized (ObjectLock.class) {
            try {
                System.out.println("do method2..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private final Object lock = new Object();

    private void method3() { // 任何对象锁
        synchronized (lock) {
            try {
                System.out.println("do method3..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final ObjectLock objLock = new ObjectLock();
        Thread t1 = new Thread(objLock::method1);
        Thread t2 = new Thread(objLock::method2);
        Thread t3 = new Thread(objLock::method3);

        t1.start();
        t2.start();
        t3.start();

    }

}
