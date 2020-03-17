package com.petrichor.java.language.other;


import java.util.ArrayList;

/**
 * @author xinyufei
 * @date 2020/1/15
 */
public class StaticStudy {

    private static void Print() {
        System.out.println("Print()");
    }

    private void Print1() {
        System.out.println("Print1()");
    }


    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();

        ((StaticStudy) null).Print(); //输出是Print().将Test引用强制转换为Test对象
        ((StaticStudy) null).Print1(); //NullPointerException. 对象还未创建，则不会有this指针的引用，因此会报空指针异常。


    }

}
