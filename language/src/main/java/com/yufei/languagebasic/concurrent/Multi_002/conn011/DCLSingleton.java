package com.yufei.languagebasic.concurrent.Multi_002.conn011;

public class DCLSingleton {

	private static DCLSingleton ds;
	
	public static DCLSingleton getDs(){
		if(ds == null){
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DCLSingleton.class) {
				if(ds == null){
					ds = new DCLSingleton();
				}
			}
		}
		return ds;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
            System.out.println(DCLSingleton.getDs().hashCode());
        },"t1");
		Thread t2 = new Thread(() -> {
            System.out.println(DCLSingleton.getDs().hashCode());
        },"t2");
		Thread t3 = new Thread(() -> {
            System.out.println(DCLSingleton.getDs().hashCode());
        },"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}
	
}
