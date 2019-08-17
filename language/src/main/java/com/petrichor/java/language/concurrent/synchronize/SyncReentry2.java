package com.petrichor.java.language.concurrent.synchronize;

/**
 * synchronized的重入
 *
 */
public class SyncReentry2 {

    static class Parent {
        int i = 10;

        synchronized void operationSup() {
            try {
                i--;
                System.out.println("Main print i = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Sub extends Parent {
        synchronized void operationSub() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println("Sub print i = " + i);
                    Thread.sleep(100);
                    this.operationSup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            Sub sub = new Sub();
            sub.operationSub();
        }).start();
    }

}
