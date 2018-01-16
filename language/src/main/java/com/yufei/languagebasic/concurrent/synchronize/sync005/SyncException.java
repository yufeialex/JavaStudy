package com.yufei.languagebasic.concurrent.synchronize.sync005;

/**
 * synchronized异常
 *
 * @author alienware
 */
public class SyncException {

    private int i = 0;

    public synchronized void operation() {
        while (true) {
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " , i = "
                        + i);
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

        final SyncException se = new SyncException();
        Thread t1 = new Thread(() -> se.operation(), "t1");
        t1.start();
    }

}
