package com.yufei.languagebasic.other;

public class ArrayTest {
    public static void main(String[] args) {
        // 如果你后面有数组的初始值，那么就不用（也不可以）指定大小。因为可能前后数量不统一
//        int [] a = new int[2]{1,3};
        int [] a = new int[]{1,3};
        // 指定个数和实际个数，只能给出一个
        int[] b = new int[2]; // 默认初始值都是0
    }
}
