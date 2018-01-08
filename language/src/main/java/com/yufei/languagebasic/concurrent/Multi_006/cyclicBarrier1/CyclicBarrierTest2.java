package com.yufei.languagebasic.concurrent.Multi_006.cyclicBarrier1;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by XinYufei on 2018/1/8.
 */
public class CyclicBarrierTest2 {
    
    static CyclicBarrier c = new CyclicBarrier(2, new A());

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

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
