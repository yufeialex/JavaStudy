package com.yufei.languagebasic.jvm.base001;

public class Test04 {

    //-Xss1m
    //-Xss5m

    //栈调用深度
    private static int count;

    private static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Throwable t) {
            System.out.println("调用最大深入：" + count);
            t.printStackTrace();
        }
    }
}
