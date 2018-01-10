package com.yufei.languagebasic.concurrent.util.lock020;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UseManyCondition {

	private ReentrantLock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	
	public void m1(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m1等待..");
			c1.await();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m1继续..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m2(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m2等待..");
			c1.await();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m2继续..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m3(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m3等待..");
			c2.await();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m3继续..");
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m4(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "唤醒..");
			// 不会立刻释放锁。会等自己结束
			c1.signalAll();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m5(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "唤醒..");
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		
		final UseManyCondition umc = new UseManyCondition();
		Thread t1 = new Thread(()-> umc.m1(),"t1");
		Thread t11 = new Thread(new Runnable() {
            @Override
            public void run() {
                umc.m1();
            }
        }, "t11");
		Thread t2 = new Thread(umc::m2,"t2");
		Thread t3 = new Thread(umc::m3,"t3");
		Thread t4 = new Thread(umc::m4,"t4");
		Thread t5 = new Thread(umc::m5,"t5");
		
		t1.start();	// c1
		t2.start();	// c1
		t3.start();	// c2
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 用join，因为等待信号，但是主线程又在这里等待，无法给信号。程序就卡死了
		/*try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("join挂了");
		}*/


		t4.start();	// c1
        // 被通知的线程，和其他线程平等竞争锁。这里就是1和2与5公平竞争
		t5.start();	// c2

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		t5.start();	// c2
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("join挂了");
		}
		System.out.println("主线程就是百老师，要检查所有人的工作，不能先结束");
	}
	
	
	
}
