package com.yufei.languagebasic.concurrent.util.future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 模拟假设，输入一本书，分词处理后，与数据库的两个图谱进行校对，但是图谱从数据库取回还要进行处理。
 * 此时就可以用future进行处理
 */
public class UseFuture implements Callable<String> {
    private String knowledgeGraphName;

    private UseFuture(String knowledgeGraphName) {
        this.knowledgeGraphName = knowledgeGraphName;
    }

    /**
     * 这里是真实的业务逻辑，其执行可能很慢
     */
    @Override
    public String call() throws Exception {
        Thread.sleep(4000);
        String result = this.knowledgeGraphName + " 构造处理完成";
        System.out.println("执行线程结束了，不care主线程");
        return result;
    }

    //主控制函数
    public static void main(String[] args) {
        //构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
        FutureTask<String> future = new FutureTask<>(new UseFuture("兽医兽药图谱"));
        FutureTask<String> future2 = new FutureTask<>(new UseFuture("纺织图谱"));

        // FutureTask继承了Runnable和Future接口，可以直接传入；
        // 它的构造函数有两个，可以传入Callable或者Runnable
        new Thread(future, "线程1").start();
        new Thread(future2, "线程2").start();
        System.out.println("多个图谱的请求已经发送...");

        // 这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
        // 主线程校验用户信息，对待处理的图书进行解析和分词
        System.out.println("现在开始并行处理输入的图书...");

//        Thread.sleep(5000);

        // 其实也可以不用之前的时间差的想法来理解；新的理解：其实就是多线程，但是特别之处是主线程会在最后等待其他线程；如果其他线程
        // 在并行的时候已经结束任务了，主线程结束自己任务时候，直接就往下走，否则就等一下。
//        System.out.println("主线程的图书处理完了，合并其他线程的图谱处理...");

        // 调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
//        System.out.println("图谱1：" + future.get()); // get就是拿实际的数据，如果没处理完，就会阻塞在这里；
//        System.out.println("图谱2：" + future2.get()); // 两个是并行的，总共用5s，而且主线程处理的1s和这5s也是并行的，打了个时间差
//        System.out.println("开始从2个图谱中查找图书的子图谱");
        System.out.println("主线程结束啦！");
    }
}
