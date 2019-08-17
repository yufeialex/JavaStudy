package com.petrichor.java.language.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionLearn {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        System.out.println(integers);
        List<Integer> collect = integers.stream()
                .map(a -> a.equals(1) ? 8 : a)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
