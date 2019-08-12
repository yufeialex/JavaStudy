package com.petrichor.languagebasic.collection;

import java.util.HashMap;

public class HashMapStudy {
    //    map的key用Integer和Long是不一样的，值一样也拿不到value
//    两个对象equals，那么hashcode一定相同，反之则不一定。
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        HashMap hashMap1 = new HashMap();
        hashMap.put(1, "One");
        hashMap.put(2, "Two");
        hashMap.put(3, "Three");
        System.out.println("Original HashMap : " + hashMap);
        hashMap1 = (HashMap) hashMap.clone();
        hashMap.put(1, "Oe");
        hashMap.put(2, "To");
        hashMap.put(3, "Thee");
        System.out.println("Original HashMap : " + hashMap);
        System.out.println("Copied HashMap : " + hashMap1);

        // shallow copy
//        mapCopy = new HashMap<>(originalMap);
//        mapCopy = (HashMap) originalMap.clone();
    }
}
