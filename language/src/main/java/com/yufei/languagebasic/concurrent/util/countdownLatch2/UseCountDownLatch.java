package com.yufei.languagebasic.concurrent.util.countdownLatch2;

import java.util.concurrent.CountDownLatch;

/**
 * 这是一个单独的示例
 */
public class UseCountDownLatch {

    public static void main(String[] args) {

        int threadCount = 10;

        final CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {

            new Thread(() -> {

                System.out.println("运动员" + Thread.currentThread().getId() + "开始出发");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("运动员" + Thread.currentThread().getId() + "已到达终点");

                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("10个运动员已经执行完毕！开始计算排名");
    }

}
