package com.yufei.languagebasic.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by XinYufei on 2017/12/26.
 */
public class Lambda {
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new HashMap<>();
//        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public static void main(String[] args) {
        ArrayList<Map> docs = new ArrayList<>();
        for(int i = 0; i < 100000; i++) {
            HashMap<Object, Object> a = new HashMap<>();
            a.put("docId", i);
            docs.add(a);
        }
        for(int i = 0; i < 100000; i++) {
            HashMap<Object, Object> a = new HashMap<>();
            a.put("docId", i);
            docs.add(a);
        }
        // 这里是返回值在反复用，不是方法反复用，所以方法里面的map是同一个
//        List<Map> docId = docs.stream().filter(distinctByKey(doc -> doc.get("docId"))).collect(Collectors.toList());
        List<Map> docId = docs.parallelStream().filter(distinctByKey(doc -> doc.get("docId"))).collect(Collectors.toList());
        System.out.println(docId.size());
//        System.out.println(docId);
    }
}
