package com.petrichor.java.language.collection;

import java.util.HashMap;

/**
 * 并发操作hashMap导致无限循环
 * 注意需要是jdk7才有这个问题
 * Created by Administrator on 2018/1/13.
 */
public class HashMapInfiniteLoop {

    private static HashMap<Integer, String> map = new HashMap<>(2);

    public static void main(String[] args) {
        map.put(5, "C");

        new Thread("Thread1") {
            public void run() {
                map.put(7, "B");
                System.out.println(map);
            }
        }.start();

        new Thread("Thread2") {
            public void run() {
                map.put(3, "A");
                System.out.println(map);
            }
        }.start();
    }
}
