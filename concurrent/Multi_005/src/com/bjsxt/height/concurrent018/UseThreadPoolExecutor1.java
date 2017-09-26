package com.bjsxt.height.concurrent018;

import java.util.concurrent.*;


public class UseThreadPoolExecutor1 {


	public static void main(String[] args) throws InterruptedException {
		/**
		 * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
		 * 若大于corePoolSize，则会将任务加入队列，
		 * 若队列已满，则创建新的线程，线程总数不能大于maximumPoolSize
		 * 若线程数大于maximumPoolSize，而且无空闲线程 则执行拒绝策略。或其他自定义方式。
		 *
		 * 在使用无界的任务队列时：LinkedBlockQueue。与有界队列相比，除非系统资源耗尽，否则无界的任务队列不存在任务入队失败的情况。
         * 当有新任务到来，系统的线程数小于 corePoolSize 时，则新建线程执行任务。当达到 corePoolSize 后，就不会继续增加。
         * 若后续仍有新的任务加入，而又没有空闲的线程资源，则任务直接进入队列等待。
         * 若任务创建和处理的速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。
		 */	
		ThreadPoolExecutor pool = new ThreadPoolExecutor(
				1, 				//coreSize
				2, 				//MaxSize
				60, 			//60
				TimeUnit.SECONDS,
//                new SynchronousQueue<>() //这个可以用
				new ArrayBlockingQueue<Runnable>(3)			//指定一种队列 （有界队列）
//				new LinkedBlockingQueue<Runnable>()
				, new MyRejected()
				//, new DiscardOldestPolicy()
				);
		
		MyTask mt1 = new MyTask(1, "任务1");
		MyTask mt2 = new MyTask(2, "任务2");
		MyTask mt3 = new MyTask(3, "任务3");
		MyTask mt4 = new MyTask(4, "任务4");
		MyTask mt5 = new MyTask(5, "任务5");
		MyTask mt6 = new MyTask(6, "任务6");
		
		pool.execute(mt1);
		pool.execute(mt2);
		pool.execute(mt3);
		pool.execute(mt4);
		pool.execute(mt5);
//        Thread.sleep(101); // 任务6永远不可能抢占第一个线程，不存在这样一个恰好的时间；它要么被拒绝，要么队列有空闲它进去；空出来的线程永远是给队列里的任务用的
        pool.execute(mt6);
		
		pool.shutdown();
		
	}
}
