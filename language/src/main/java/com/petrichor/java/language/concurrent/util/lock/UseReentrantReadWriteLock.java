package com.petrichor.java.language.concurrent.util.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 读读共享,读写互斥,写写互斥
 */
public class UseReentrantReadWriteLock {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReadLock readLock = rwLock.readLock();
    private WriteLock writeLock = rwLock.writeLock();

    private void read() {
        common(readLock);
    }

    private void write() {
        common(writeLock);
    }

    private void common(Lock lock) {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final UseReentrantReadWriteLock urrw = new UseReentrantReadWriteLock();

        Thread t1 = new Thread(urrw::read, "t1");
        Thread t2 = new Thread(urrw::read, "t2");
        Thread t3 = new Thread(urrw::write, "t3");
        Thread t4 = new Thread(urrw::write, "t4");

        t1.start();
        t2.start();

//		t1.start(); // R 
//		t3.start(); // W

        t3.start();
        t4.start();
    }
}
