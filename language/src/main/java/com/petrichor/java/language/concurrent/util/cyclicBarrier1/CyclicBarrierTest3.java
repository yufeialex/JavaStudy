package com.petrichor.java.language.concurrent.util.cyclicBarrier1;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by XinYufei on 2018/1/8.
 */
public class CyclicBarrierTest3 {
    private static CyclicBarrier c = new CyclicBarrier(2);


    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        Thread thread = new Thread(() -> {
            try {
                c.await();
            } catch (Exception e) {
            }
        });

        thread.start();
        thread.interrupt();
        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }
    }
}
