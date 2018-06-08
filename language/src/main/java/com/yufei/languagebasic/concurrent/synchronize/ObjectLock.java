package com.yufei.languagebasic.concurrent.synchronize;

/**
 * 使用synchronized代码块加锁,比较灵活
 *
 * @author alienware
 */
public class ObjectLock {
    // 这里就是有一个类里有好几个方法都需要加锁，但是希望它们能同时被调用，
    // 那么就用别的对象给它加锁，因为一般加锁只能用自身这个对象嘛，
    // 加上自己的类也才两个，那就再new一个Object对象就行了

    public void method1() {
        synchronized (this) { // 对象锁
            try {
                System.out.println("do method1..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void method2() { // 类锁
        synchronized (ObjectLock.class) {
            try {
                System.out.println("do method2..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object lock = new Object();

    public void method3() { // 任何对象锁
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
