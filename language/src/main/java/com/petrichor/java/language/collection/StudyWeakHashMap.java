package com.petrichor.java.language.collection;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * WeakHashMap实现了Map接口，是HashMap的一种实现，它比HashMap多了一个引用队列：
 * <p>
 * WeakHashMap中方法的实现方式基本和HashMap的一样，注意“基本”两个字，除了没有实现Cloneable和Serializable这两个标记接口，
 * 最大的区别在于在于expungeStaleEntries()这个方法，这个是整个WeakHashMap的精髓
 * <p>
 * Created by XinYufei on 2018/1/3.
 */
public class StudyWeakHashMap {
    public static void main(String[] args) {
        func1();
    }

    private static void func1() {
        Map<String, Integer> map = new WeakHashMap<>();
        map.put("s1", 1);
        map.put("s2", 2);
        map.put("s3", 3);
        map.put("s4", 4);
        map.put("s5", 5);
        map.put(null, 9);
        map.put("s6", 6);
        map.put("s7", 7);
        map.put("s8", 8);
        map.put(null, 11);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println(map);
    }

    void func2() {
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            map.put(ii, new byte[i]);
        }
    }

    void func3() {
        Map<Integer, Object> map = new WeakHashMap<>();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            map.put(ii, new byte[i]);
        }
    }

    // 如果存放在WeakHashMap中的key都存在强引用，那么WeakHashMap就会退化成HashMap
    void func4() {
        Map<Integer, Object> map = new WeakHashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Integer ii = new Integer(i);
            list.add(ii);
            map.put(ii, new byte[i]);
        }
    }

    void func5() throws InterruptedException {
        Map<Object, Object> map = new WeakHashMap<>();
        map.put(null, new byte[5 * 1024 * 928]);
        int i = 1;
        while (true) {
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
            System.out.println(map.size());
            System.gc();
            System.out.println("==================第" + i++ + "次GC结束====================");
        }
    }

    void func6() throws InterruptedException {
        Map<Integer, Object> map = new WeakHashMap<>();
        System.gc();
        System.out.println("===========gc:1=============");
        map.put(null, new byte[4 * 1024 * 1024]);
        TimeUnit.SECONDS.sleep(5);
        System.gc();
        System.out.println("===========gc:2=============");
        TimeUnit.SECONDS.sleep(5);
        System.gc();
        System.out.println("===========gc:3=============");
        map.remove(null);
        TimeUnit.SECONDS.sleep(5);
        System.gc();
        System.out.println("===========gc:4=============");
    }
}
