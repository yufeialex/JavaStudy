package com.yufei.languagebasic.concurrent.Multi_006.concurrent019;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 模拟假设，输入一本书，分词处理后，与数据库的两个图谱进行校对，但是图谱从数据库取回还要进行处理。
 * 此时就可以用future进行处理
 */
public class UseFuture implements Callable<String> {
    private String knowledgeGraphName;

    public UseFuture(String knowledgeGraphName) {
        this.knowledgeGraphName = knowledgeGraphName;
    }

    /**
     * 这里是真实的业务逻辑，其执行可能很慢
     */
    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        String result = this.knowledgeGraphName + " 构造处理完成";
        return result;
    }

    //主控制函数
    public static void main(String[] args) throws Exception {
        //构造FutureTask，并且传入需要真正进行业务逻辑处理的类,该类一定是实现了Callable接口的类
        FutureTask<String> future = new FutureTask<>(new UseFuture("兽医兽药图谱"));
        FutureTask<String> future2 = new FutureTask<>(new UseFuture("纺织图谱"));
        new Thread(future, "线程1").start();
        new Thread(future2, "线程2").start();
        System.out.println("图谱请求完毕");
        // 这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
        // 主线程校验用户信息，对待处理的图书进行解析和分词
        System.out.println("处理输入的图书...");
        Thread.sleep(5000);
        System.out.println("图书处理完了，时间差打到这里为止了，如果派出去干活的小弟已经完事，那么我就不用等了。否则还得等他们...");
        // 调用获取数据方法,如果call()方法没有执行完成,则依然会进行等待
        System.out.println("图谱1：" + future.get()); // get就是拿实际的数据，如果没处理完，就会阻塞在这里；
        System.out.println("图谱2：" + future2.get()); // 两个是并行的，总共用5s，而且主线程处理的1s和这5s也是并行的，打了个时间差
        System.out.println("开始从2个图谱中查找图书的子图谱");
    }
}
