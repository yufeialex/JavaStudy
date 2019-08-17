package com.petrichor.java.language.concurrent.util.cyclicBarrier3;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计算List中所有整数的和<br>
 * 采用多线程，分割List计算
 *
 * @author 飞雪无情
 * @since 2010-7-12
 */
class CountListIntegerSum {
    private long sum;//存放整数的和
    private CyclicBarrier barrier;//障栅集合点(同步器)
    private List<Integer> list;//整数集合List
    private int threadCounts;//使用的线程数

    public CountListIntegerSum(List<Integer> list, int threadCounts) {
        this.list = list;
        this.threadCounts = threadCounts;
    }

    /**
     * 获取List中所有整数的和
     *
     * @return
     */
    public long getIntegerSum() {
        ExecutorService exec = Executors.newFixedThreadPool(threadCounts);
        int len = list.size() / threadCounts;//平均分割List
        //List中的数量没有线程数多（很少存在）
        if (len == 0) {
            threadCounts = list.size();//采用一个线程处理List中的一个元素
            len = list.size() / threadCounts;//重新平均分割List
        }
        barrier = new CyclicBarrier(threadCounts + 1);
        for (int i = 0; i < threadCounts; i++) {
            //创建线程任务
            if (i == threadCounts - 1) {//最后一个线程承担剩下的所有元素的计算
                exec.execute(new SubIntegerSumTask(list.subList(i * len, list.size())));
            } else {
                exec.execute(new SubIntegerSumTask(list.subList(i * len, len * (i + 1) > list.size() ? list.size() : len * (i + 1))));
            }
        }
        try {
            barrier.await();//关键，使该线程在障栅处等待，直到所有的线程都到达障栅处
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ":Interrupted");
        } catch (BrokenBarrierException e) {
            System.out.println(Thread.currentThread().getName() + ":BrokenBarrier");
        }
        exec.shutdown();
        return sum;
    }

    /**
     * 分割计算List整数和的线程任务
     *
     * @author lishuai
     */
    class SubIntegerSumTask implements Runnable {
        private List<Integer> subList;

        SubIntegerSumTask(List<Integer> subList) {
            this.subList = subList;
        }

        public void run() {
            long subSum = 0L;
            for (Integer i : subList) {
                subSum += i;
            }
            synchronized (CountListIntegerSum.this) {//在CountListIntegerSum对象上同步
                sum += subSum;
            }
            try {
                barrier.await();//关键，使该线程在障栅处等待，直到所有的线程都到达障栅处
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + ":Interrupted");
            } catch (BrokenBarrierException e) {
                System.out.println(Thread.currentThread().getName() + ":BrokenBarrier");
            }
            System.out.println("分配给线程：" + Thread.currentThread().getName() + "那一部分List的整数和为：\tSubSum:" + subSum);
        }

    }

    /*



      以前写过类似这样的东西 但是没有CyclicBarrier类
     ExecutorService 的 shutdown 方法应该在 barrier.await(); 前面

     发现的问题：
     (1)CyclicBarrier不适合这种并发任务。单个线程完成任务之后完全可以终止了，没必要全部等待着，这可能是很大的资源浪费。使用CountDownLatch也会有这个问题。
     (2)楼主使用了线程同步，考虑到同步的代价，这是可能是个很大的时间浪费。
     (3)楼主使用CyclicBarrier的唯一用处在于，保证所有的任务都完成了。但是杀鸡焉用牛刀?这可能是...的浪费

     其实实现这样的功能，可以不用那么复杂，而且可以不用加锁。这里需要一个AtomicInteger(设为atom)。每个线程获得自己的sublist求和任务之后，计算一个和，
     保存到某个成员变量(设为subsum)中(如某楼上所说)，把那个atom加1，然后结束。
     在主方法中，启动所有线程，然后添加一条语句，等待所有线程完成任务：
     while(atom.intValue()<threadCounts)
     Thread.yield();
     最后从所有死掉的线程对象中，获取subsum并累加即可。




     包含所有整数的List没必要拆分为一个个小的List, 只要算出各自计算的index范围, 第一计算1-100个, 第二个计算101-200个, 从同一个List中取值

     sublist本身的消耗是非常大的，在这个场景中完全没有必要，可以将index范围和大List作为Runnable的属性注入，计算时直接调用get(index)方法即可

     将大的list分块1-n,n-2n,......,取决于线程数，都是计算型任务，线程数最好使用cpu 核心数。


     我感觉使用一个index范围和使用一个subList差不多，都是拆分成了小块，只不过一个用范围标记的，一个直接是一个List小块。。List的subList函数消耗也不大。下面是生成SubList的源代码，只是进行了一下索引以为，比for循环get(i)取值要好得多。

     SubList(AbstractList<E> list, int fromIndex, int toIndex) {
     if (fromIndex < 0)
     throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
     if (toIndex > list.size())
     throw new IndexOutOfBoundsException("toIndex = " + toIndex);
     if (fromIndex > toIndex)
     throw new IllegalArgumentException("fromIndex(" + fromIndex +
     ") > toIndex(" + toIndex + ")");
     l = list;
     offset = fromIndex;
     size = toIndex - fromIndex;
     expectedModCount = l.modCount;
     }




     List<int> collection = new List<int>();
     ...
     return collection.AsParallel<int>().Sum();

     这种东西在C#里根本不值得去浪费脑细胞。


     两个int相加，都可能越界，何况“很大的List”。所以实战的话，可能需要BigInteger。
     而BigInteger的话，更体现了线程的优势。N个较小的BI相加，比一个巨大的BI参与的计算快多了。
     同意你说的越界问题，不过采用BigInteger我倒没试过，因为它的加减乘除等操作是程序实现的，不一定比（+-*除）操作符快吧。
     BigInteger我做过阶乘，分组相乘，(1*9*17...) * (2*10*18...) 速度超快。


     其实这种纯粹的算术相加速度单线程和多线程是一样的（因为没有io读写，没有网络等待等等，），新建线程还要消耗资源。这种计算，
     直接用单线程，面试官问了就这么说。
     如果该问题是从10000个文件（更比如说是webservice从别的异构系统读出整数）读出数字相加，我肯定会用lz的方法，
     因为读取文件需要io时间，这些线程等待的时候，其他线程还可以进行运算，不会像单线程阻塞住，这时候才是多线程应用的场景，个人看法


     Java1.2之后，Java多线程依赖于操作系统的内核线程调度。操作系统会不会发挥多处理的优势呢？肯定会啊，操作系统作为基础设施，实时性是它最重视之一。
     针对于你的观点，我进行反驳：
     1.在进程之间，多个JVM的Heap不能相互共享。更谈不上制定那个CPU制定JVM进行，要知道主存是共享的，CPU的处理数据的。多核CPU不是多台机器，那个CPU负责，是由OS调度，用户进程没有办法控制内核进程。

     2.List#sublist方法实现，当大List分离出多个小List，小List并没有放弃大的List，而是还是引用的大List。在计算之中，不会被GC掉。之后被GC掉，对计算没有影响。调整JVM需要是对的，但是调整是Java Heap的大小，而不是为了GC。如果要说GC的话，计算中虽然不会被GC，但是GC会停顿（Pause/Stop World操作），丧失了实时性。

     */

}
