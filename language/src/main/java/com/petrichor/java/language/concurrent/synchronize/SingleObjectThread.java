package com.petrichor.java.language.concurrent.synchronize;

/**
 * 线程安全概念：当多个线程访问某一个类（对象或方法）时，这个对象始终都能表现出正确的行为，那么这个类（对象或方法）就是线程安全的。
 * synchronized：可以在任意对象及方法上加锁，而加锁的这段代码称为"互斥区"或"临界区"

 */
public class SingleObjectThread extends Thread {

    private int count = 5;
    private int count1 = 50;

    public synchronized void run() {
        //就是多个线程访问的都是这个对象的这个变量，所以需要加锁，所的是共享资源，或者互斥操作
        count--;
        System.out.println(SingleObjectThread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
		/*
		  分析：当多个线程访问myThread的run方法时，以排队的方式进行处理（这里排对是按照CPU分配的先后顺序而定的），
		  一个线程想要执行synchronized修饰的方法里的代码：

		  1 尝试获得锁
		  2 如果拿到锁，执行synchronized代码体内容；拿不到锁，这个线程就会不断的尝试获得这把锁，直到拿到为止，
		  而且是多个线程同时去竞争这把锁。（也就是会有锁竞争的问题）
		 */
        SingleObjectThread myThread = new SingleObjectThread();
        Thread t1 = new Thread(myThread, "t1");
        Thread t2 = new Thread(myThread, "t2");

        //2个线程访问的都是myThread这个对象
        t1.start();
        t2.start();
    }
}
