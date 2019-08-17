package com.petrichor.java.language.collection;

import java.util.ArrayList;

/**
 * 测试容器类能否接受null值
 * Created by XinYufei on 2018/1/15.
 */
public class CollectionAndNulTest {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(null);
        integers.add(null);
        System.out.println(integers.size());
    }

}
