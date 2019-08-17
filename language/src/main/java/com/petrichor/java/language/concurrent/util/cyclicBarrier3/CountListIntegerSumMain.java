package com.petrichor.java.language.concurrent.util.cyclicBarrier3;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算List中所有整数的和测试类
 *
 * @author 飞雪无情
 * @since 2010-7-12
 */
public class CountListIntegerSumMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        int threadCounts = Runtime.getRuntime().availableProcessors();//采用的线程数
        //生成的List数据
        for (int i = 1; i <= 10000000; i++) {
            list.add(i);
        }
        CountListIntegerSum countListIntegerSum = new CountListIntegerSum(list, threadCounts);
        long sum = countListIntegerSum.getIntegerSum();

        long end = System.currentTimeMillis();

        System.out.println("List中所有整数的和为:" + sum);
        System.out.println("所用时间:" + (end - start) / 1.0e3);
    }

}

/*
  其他思路
  <p>
  <p>
  你的内部类实现 callable 接口 然后Call 会返回值
  ExecutorService 的submit方法可以返回一个 Future 对象
  Future 对象来实现同步很好用的 可以尝试下
  用 Futrue 的 isDone 来判断线程是否工作结束
  <p>
  可以用atomic来替代count的synchronized
  <p>
  fork/join处理这个貌似是最合适的
  <p>
  主线程用CountDownLatch等待结果，结果放在CopyOnWriteList里面
  <p>
  每个线程存放自己相加的结果，计算完以后，再相加各个线程统计的结果，避免了同步。
  <p>
  <p>
  搞个map-reduce就好了
  <p>
  ExecutorCompletionService和Executor配合就行了。
  CyclicBarrier和CountDownLatch和BlockingQueue提供了相同的语义，条件阻塞，说白了还是AbstractQuquedSynchronizor的实现。
  其实性能还可以再提高的，Cyclicbarrier运算完毕后都要await是很讨厌的。
  应该使用CompletionService,然后这样
  long count = 0L;
  for(int i=0;i<segCount;i++){
  count += cs.take().get();
  }
  使用ExecutorService.invokeAll的缺点是，如果第一个任务需要花很多时间，可能不得不等待
  使用这个会一个一个的遍历，如果某个任务还没有完成就会等待
  应该使用ExecutorCompleteionService，会直接获取下一个已经完成的task，如果没有任何一个task是完成的话，再等待。
 */