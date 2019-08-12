package com.petrichor.languagebasic.concurrent.util.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseCondition {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void method1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入等待状态..");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "await，释放锁..");
            condition.await();    // Object wait
            System.out.println(Thread.currentThread().getName() + "await收到信号，继续执行...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void method2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入..");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "发出唤醒..");
            // 不会立刻释放锁。会等自己结束
            condition.signal();        //Object notify
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void method3() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "进入..");
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final UseCondition uc = new UseCondition();
        new Thread(uc::method1, "t1").start();
        new Thread(uc::method2, "t2").start();
        new Thread(uc::method3, "t3").start();
    }


}
