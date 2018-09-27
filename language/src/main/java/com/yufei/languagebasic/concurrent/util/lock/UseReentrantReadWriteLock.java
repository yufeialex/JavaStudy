package com.yufei.languagebasic.concurrent.util.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class UseReentrantReadWriteLock {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private ReadLock readLock = rwLock.readLock();
    private WriteLock writeLock = rwLock.writeLock();

    public void read() {
        common(readLock);
    }

    public void write() {
        common(writeLock);
    }

    private void common(Lock lock) {
        try {
            lock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
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
