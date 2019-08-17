package com.petrichor.java.language.java8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by XinYufei on 2017/12/26.
 * 当下互联网技术成熟，越来越多的趋向去中心化、分布式、流计算，使得很多以前在数据库侧做的事情放到了Java端。
 * 如果数据库字段没有索引，那么应该如何根据该字段去重？
 */
public class StatefulFilter {
    //    a stateful filter
    /*
    It's static because it doesn't depend on anything from the class in which it's declared.
    As such it can be in a utility class or something. I don't think it's a problem for it to be a non-static method,
    but it does seem a bit odd in that calls to foo.distinctByKey() and bar.distinctByKey() don't have anything to do
    with the foo or bar instances
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        // 这里是返回值在反复用，不是方法反复用，所以方法里面的map是同一个
        //  if the stream is ordered and is run in parallel, this will preserve an arbitrary element from among the duplicates,
        //  instead of the first one, as distinct() does.
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        // returns a predicate that maintains state about what it's seen previously,
        // and that returns whether the given element was seen for the first time:
        return t -> seen.add(keyExtractor.apply(t));

        /*Map<Object, Boolean> map = new HashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;*/
    }

    /**
     * 需求是根据文档id去重。入参的list中，有重复的map，通过id可以判断。希望得到一个不重复的list
     */
    public static void main(String[] args) {
        // 20w个对象，有10w都是重复的。
        ArrayList<Map> docs = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            HashMap<Object, Object> a = new HashMap<>();
            a.put("docId", i);
            docs.add(a);
        }
        for (int i = 0; i < 100000; i++) {
            HashMap<Object, Object> a = new HashMap<>();
            a.put("docId", i);
            docs.add(a);
        }
        // 这里是返回值在反复用，不是方法反复用，所以方法里面的map是同一个
        /*List<Map> docId = docs.stream()
                .filter(distinctByKey(doc -> doc.get("docId")))
                .collect(Collectors.toList());
        System.out.println(docId.size());*/

        List<Map> docId1 = docs.parallelStream()
                .filter(distinctByKey(doc -> doc.get("docId")))
                .collect(Collectors.toList());
        System.out.println(docId1.size());
    }
}
