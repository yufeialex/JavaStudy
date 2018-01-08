package com.yufei.languagebasic.concurrent.Multi_006.concurrent019;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 和UseFuture2文件的区别是用了线程池，但是没有用futureTask
 *
 * 模拟假设，输入一本书，分词处理后，与数据库的两个图谱进行校对，但是图谱从数据库取回还要进行处理。
 * 此时就可以用future进行处理
 */
public class UseFuture1 implements Callable<String>{
	private String knowledgeGraphName;

	public UseFuture1(String knowledgeGraphName){
		this.knowledgeGraphName = knowledgeGraphName;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(5000);
		String result = this.knowledgeGraphName + " 构造处理完成";
		return result;
	}
	
	//主控制函数
	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		// 这里返回的f1和f2能用get
        Future f1 = executor.submit(new UseFuture1("兽医兽药图谱"));
        Future f2 = executor.submit(new UseFuture1("纺织图谱"));
		System.out.println("图谱请求完毕");
		System.out.println("校验用户信息，对待处理的图书进行解析和分词...");
		Thread.sleep(5000);
		System.out.println("图书处理完了，时间差打到这里为止了，如果派出去干活的小弟已经完事，那么我就不用等了。否则还得等他们...");
		System.out.println("图谱1：" + f1.get());
		System.out.println("图谱2：" + f2.get());
		System.out.println("开始从2个图谱中查找图书的子图谱");
		executor.shutdown();
	}
}
