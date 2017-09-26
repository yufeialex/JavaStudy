package com.bjsxt.base.sync006;
/**
 * 锁对象的改变问题
 * @author alienware
 *
 */
public class ChangeLock {

	private final String lock = "lock"; // idea建议改成final，就是为了避免锁对象的改变
	
	private void method() {
		synchronized (lock) { // 这里的锁对象换成this，锁就不会变，就是同步的
            System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
//            lock = "change lock"; // 这里锁改变了，就不同步了
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
	
		final ChangeLock changeLock = new ChangeLock();
		Thread t1 = new Thread(changeLock::method, "t1");
		Thread t2 = new Thread(changeLock::method, "t2");

		t1.start();
		Thread.sleep(100);
		t2.start();
	}
	
}
