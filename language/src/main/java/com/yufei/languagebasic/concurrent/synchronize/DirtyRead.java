package com.yufei.languagebasic.concurrent.synchronize;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * 业务要考虑整体性，对于变量的set和get方法肯定要一起同步
 * <p>
 * 一个线程修改到一半，另一个线程读取到了修改一半的结果。
 * 类似数据库事务，A事务修改到一半的时候，B事务不应该看到结果
 * <p>
 * 系统中老婆在修改我的账号，但是改到一半我就去读取了。导致错误
 */
public class DirtyRead {

	private String username = "xyf";
	private String password = "123";

	public synchronized void setValue(String username, String password) throws InterruptedException {
		this.username = username;
		Thread.sleep(2000); // 这里使用复杂算法计算密码值
		this.password = password;
		System.out.println("setValue最终结果：username = " + username + " , password = " + password);
	}

	public void getValue() {
//        Thread.sleep(5000); // 这里为了显示主线程读取的时候，wifeThread线程在等待
		System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
	}

	public static void main(String[] args) throws Exception {
		final DirtyRead dr = new DirtyRead(); // 这个就是共享资源
		Runnable wifeChange = () -> {
            try {
                dr.setValue("ljw", "456");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(wifeChange, "wifeThread").start();
        // 这里就是为了让wifeThread线程先启动起来，是一种模拟；模拟真实环境中的一种情况。
        // 如果不设置，那么主线程先执行，拿到的是原来的结果，都是对的。主线程在取值的时候，wifeThread线程要等待
		Thread.sleep(1000);
		dr.getValue();
	}

}
