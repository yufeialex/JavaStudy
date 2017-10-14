package com.yufei.languagebasic.concurrent.Multi_001.sync004;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * 
 * @author alienware
 *
 */
public class DirtyRead {

	private String username = "bjsxt";
	private String password = "123";

	public synchronized void setValue(String username, String password) {
		this.username = username;

		try {
			Thread.sleep(2000); //这里就模拟实际业务中，做了一部分操作，中间耗时，后一部分没修改就被别的线程读取了共享资源
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.password = password;

		System.out.println("setValue最终结果：username = " + username
				+ " , password = " + password);
	}

	public  void getValue() throws InterruptedException { // 业务要考虑整体性，对于变量的set和get方法肯定要一起同步
//        Thread.sleep(5000); // 这里为了显示主线程读取的时候，t1线程在等待
		System.out.println("getValue方法得到：username = " + this.username
				+ " , password = " + this.password);
	}

	public static void main(String[] args) throws Exception {

		final DirtyRead dr = new DirtyRead();
		Thread t1 = new Thread(() -> dr.setValue("z3", "456"));
		t1.start();

        // 这里就是为了让t1线程先启动起来，是一种模拟；模拟真实环境中的一种情况。
        // 如果不设置，那么主线程先执行，拿到的是原来的结果，都是对的。主线程在取值的时候，t1线程要等待
		Thread.sleep(1000);

		dr.getValue();
	}

}
