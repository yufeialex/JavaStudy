package com.petrichor.languagebasic.jvm;

/**
 * Created by XinYufei on 2018/1/11.
 */
public class TestXSS {

    private static long count = 0;

    public static void main(String[] args) {
        infinitelyRecursiveMethod(1);

    }

    private static void infinitelyRecursiveMethod(long a) {
        System.out.println(count++);
        infinitelyRecursiveMethod(a);
    }
}
