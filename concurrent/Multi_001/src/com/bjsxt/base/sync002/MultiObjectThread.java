package com.bjsxt.base.sync002;

/**
 * 关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所属对象的锁（Lock），
 * 
 * 在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）。
 * 
 * @author alienware
 *
 */
public class MultiObjectThread {

	private static int num = 0;

	/** static */
	public static synchronized void printNum(String tag) { // 加了static就是类锁了
		try {
			if (tag.equals("a")) {
				num = 100;
				System.out.println("tag a, set num over!");
				Thread.sleep(1000);
			} else {
				num = 200;
				System.out.println("tag b, set num over!");
			}

			System.out.println("tag " + tag + ", num = " + num);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 注意观察run方法输出顺序
	public static void main(String[] args) {

		// 俩个不同的对象
		final MultiObjectThread m1 = new MultiObjectThread();
		final MultiObjectThread m2 = new MultiObjectThread();

		Thread t1 = new Thread(() -> {m1.printNum("a");});
		Thread t2 = new Thread(() -> {m2.printNum("b");});

		t1.start(); //t2不会等待t1因为是两个对象的锁
		t2.start();
	}
}
