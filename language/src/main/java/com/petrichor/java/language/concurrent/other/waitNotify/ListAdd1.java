package com.petrichor.java.language.concurrent.other.waitNotify;

import java.util.ArrayList;
import java.util.List;

public class ListAdd1 {

    // 没有wait和notify的时候，两个线程利用了异常进行通信
    // 需要通信就要有共同的东西，大家都能访问到的，这里是用了list
    private volatile static List<String> list = new ArrayList<>();

    private void add() {
        list.add("bjsxt");
    }

    private int size() {
        return list.size();
    }

    public static void main(String[] args) {

        final ListAdd1 list1 = new ListAdd1();

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    list1.add();
                    System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (true) {
                if (list1.size() == 5) {
                    System.out.println("当前线程收到通知：" + Thread.currentThread().getName() + " list size = 5 线程停止..");
                    throw new RuntimeException();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }


}
