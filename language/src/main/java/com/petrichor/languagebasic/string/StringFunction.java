package com.petrichor.languagebasic.string;

import java.util.stream.Stream;

public class StringFunction {
    //    public static void main是因为静态的不需要创建实例
    public static void main(String[] args) {
        StringFunction sf = new StringFunction();
        sf.splitTest();

        int a = 10;
        String b = a + ""; //转换成字符串的一个小技巧
    }

    // 前面的逗号都是有意义的，后面的逗号是没有意义的
    private void splitTest() {
        String a = ",北京";
        String b = "北京,";
        String[] splita = a.split(","); // 长度2，""和北京
        String[] splitb = b.split(","); // 长度1，只有北京

        System.out.println("------------");
        Stream.of(splita).forEach(System.out::println);
        System.out.println("------------");
        Stream.of(splitb).forEach(System.out::println);
        System.out.println("------------");

        // 因为参数是正则表达式，所以特殊字符串需要转义
        String[] paraStr = "6010|320100|A".split("\\|");// 不转义也能运行，但是会挨个分割，因为把空串当成分隔符
        String[] paraStr1 = "6010+320100+A".split("\\+"); // 不专一直接报错

        // 或者用这种方式
        String[] paraStr2 = "6010|320100|A".split("[|]");
        String[] paraStr3 = "6010.320100.A".split("[.]");
    }

//    replaceAll能用正则，replace不能
//    valueOf参数为空返回null字符串


}
