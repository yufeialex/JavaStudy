package com.yufei.languagebasic.concurrent.util.cyclicBarrier1;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by XinYufei on 2018/1/8.
 */
public class CyclicBarrierTest1 {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                c.await();
            } catch (Exception e) {
            }
            System.out.println(1);
        }).start();

        try {
            c.await();
        } catch (Exception e) {
        }
        System.out.println(2);
    }
}
