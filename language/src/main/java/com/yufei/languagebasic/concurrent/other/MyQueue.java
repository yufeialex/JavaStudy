package com.yufei.languagebasic.concurrent.other;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

    //1 需要一个承装元素的集合
    private LinkedList<Object> list = new LinkedList<>();

    //2 需要一个计数器
    private AtomicInteger count = new AtomicInteger(0);

    //3 需要制定上限和下限
    private final int minSize = 0;

    private final int maxSize;

    //4 构造方法
    public MyQueue(int size) {
        this.maxSize = size;
    }

    //5 初始化一个对象 用于加锁
    private final Object lock = new Object();

    // put(anObject): 把anObject加到BlockingQueue里,如果BlockQueue没有空间,
    // 则调用此方法的线程被阻断，直到BlockingQueue里面有空间再继续.
    public void put(Object obj) {
        synchronized (lock) {
            while (count.get() == this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //1 加入元素
            list.add(obj);
            //2 计数器累加
            count.incrementAndGet();
            //3 通知另外一个线程（唤醒）
            lock.notify();
            System.out.println("新加入的元素为:" + obj);
        }
    }

    //take: 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,
    // 阻断进入等待状态直到BlockingQueue有新的数据被加入.
    public Object take() {
        Object ret = null;
        synchronized (lock) {
//			这里不能用if，因为阻塞之后，会再次判断条件，如果还是满足条件，那么会再次wait；但是if就直接跳到后面执行了
            while (count.get() == this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //1 做移除元素操作
            ret = list.removeFirst();
            //2 计数器递减
            count.decrementAndGet();
            //3 唤醒另外一个线程
            lock.notify();
            System.out.println("取走的元素为:" + ret);
        }
        return ret;
    }

    public int getSize() {
        return this.count.get();
    }

    public static void main(String[] args) {

        final MyQueue mq = new MyQueue(5);
        mq.put("a");
        mq.put("b");
        mq.put("c");
        mq.put("d");
        mq.put("e");

        System.out.println("当前容器的长度:" + mq.getSize());

        Thread t1 = new Thread(() -> {
            mq.put("f");
            mq.put("g");
        }, "t1");

        t1.start();

        Thread t2 = new Thread(() -> {
            mq.take();
            mq.take();
        }, "t2");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.start();
    }
}
