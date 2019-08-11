package com.yufei.languagebasic.concurrent.synchronize;

/**
 * synchronized异常
 *

 */
public class SyncException {

    private int i = 0;

    private synchronized void operation() {
        while (true) {
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " , i = " + i);
                if (i == 20) {
                    Integer.parseInt("a");
                    // throw new RuntimeException();
                }
            } catch (Exception e) { // InterruptedException e
                e.printStackTrace();
                System.out.println("log info: i = " + i);
                throw new RuntimeException();
                // continue;
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new SyncException()::operation, "t1").start();
    }

}
