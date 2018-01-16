package com.yufei.languagebasic.concurrent.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class UseDeque {

    public static void main(String[] args) {

        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<>();
        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");
        chm.putIfAbsent("k4", "vvvv");


        LinkedBlockingDeque<String> dq = new LinkedBlockingDeque<>(10);
        dq.addFirst("a");
        dq.addFirst("b");
        dq.addFirst("c");
        dq.addFirst("d");
        dq.addFirst("e");
        dq.addLast("f");
        dq.addLast("g");
        dq.addLast("h");
        dq.addLast("i");
        dq.addLast("j");
        //dq.offerFirst("k");
        System.out.println("查看头元素：" + dq.peekFirst());
        System.out.println("获取尾元素：" + dq.pollLast());
//		Object [] objs = dq.toArray();
//		for (Object obj : objs) {
//			System.out.println(obj);
//		}
        dq.forEach(System.out::println);
    }
}
