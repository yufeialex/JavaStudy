package com.yufei.languagebasic.concurrent.util.future;

import java.util.concurrent.*;

/**
 * 和UseFuture文件的区别是用了线程池
 * <p>
 * 模拟假设，输入一本书，分词处理后，与数据库的两个图谱进行校对，但是图谱从数据库取回还要进行处理。
 * 此时就可以用future进行处理
 */
public class UseFuture2 implements Callable<String> {
    private String knowledgeGraphName;

    public UseFuture2(String knowledgeGraphName) {
        this.knowledgeGraphName = knowledgeGraphName;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        String result = this.knowledgeGraphName + " 构造处理完成";
        return result;
    }

    public static void main(String[] args) throws Exception {
        FutureTask<String> future = new FutureTask<>(new UseFuture2("兽医兽药图谱"));
        FutureTask<String> future2 = new FutureTask<>(new UseFuture2("纺织图谱"));
        ExecutorService ex = Executors.newFixedThreadPool(2);
        // 返回的f1和f2会get不到对象，不能用；线程池纯粹是替代了new Thread(future).start()这句话而已
        Future f1 = ex.submit(future);
        Future f2 = ex.submit(future2);
        System.out.println("图谱请求完毕");
        System.out.println("校验用户信息，对待处理的图书进行解析和分词...");
        Thread.sleep(5000);
        System.out.println("图书处理完了，时间差打到这里为止了，如果派出去干活的小弟已经完事，那么我就不用等了。否则还得等他们...");
        System.out.println("图谱1：" + future.get());
        System.out.println("图谱2：" + future2.get());
        System.out.println("开始从2个图谱中查找图书的子图谱");

        ex.shutdown();
    }
}
