package com.bjsxt.height.concurrent019;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UseFuture implements Callable<String>{
	private String para;
	
	public UseFuture(String para){
		this.para = para;
	}
	
	/**
	 * 这里是真实的业务逻辑，其执行可能很慢
	 */
	@Override
	public String call() throws Exception {
		//模拟执行耗时
		Thread.sleep(5000);
		String result = this.para + "处理完成";
		return result;
	}
	
	//主控制函数
	public static void main(String[] args) throws Exception {
		String queryStr = "query";
		
		//构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
//		FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));
//		FutureTask<String> future2 = new FutureTask<String>(new UseFuture(queryStr));
		
		//创建一个固定线程的线程池且线程数为1,
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		// 这里提交任务future,则开启线程执行RealData的call()方法执行
		// submit和execute的区别：
		// 第一点是submit可以传入实现Callable接口的实例对象，后者只能是runnable
		// 第二点是submit方法有返回值
//		Future f1 = executor.submit(future);		//单独启动一个线程去执行的
//		Future f2 = executor.submit(future2);

        Future f1 = executor.submit(new UseFuture(queryStr));		//单独启动一个线程去执行的
        Future f2 = executor.submit(new UseFuture(queryStr));
		System.out.println("请求完毕");
		
		try {
			//这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
			System.out.println("处理实际的业务逻辑...");
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
//		System.out.println("数据：" + future.get()); // get就是拿实际的数据，如果没处理完，就会阻塞在这里；
//		System.out.println("数据：" + future2.get()); // 两个是并行的，总共用5s，而且主线程处理的1s和这5s也是并行的，打了个时间差
		
		System.out.println("数据：" + f1.get()); // get就是拿实际的数据，如果没处理完，就会阻塞在这里；
		System.out.println("数据：" + f2.get()); // 两个是并行的，总共用5s，而且主线程处理的1s和这5s也是并行的，打了个时间差

		System.out.println("sssss");
		
		executor.shutdown();
	}
}
