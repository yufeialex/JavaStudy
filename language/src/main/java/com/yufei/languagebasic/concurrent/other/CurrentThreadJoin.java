package com.yufei.languagebasic.concurrent.other;

/**
 * Created by XinYufei on 2018/1/12.
 * 仔细研究了下Thread.currentThread().join();
 * 当前线程等待当前线程结束？
 * <p>
 * while (isAlive()) {
 * wait(0);
 * }
 * <p>
 * 实际上是当前线程等待自己die:因为一直等待，所以无法die；因为没有die，所以还在wait(0)
 * <p>
 * 如果企图在其他线程Notify，会报java.lang.IllegalMonitorStateException，同时，它还在无限制的等待自己die
 * <p>
 * <p>
 * 所以这个代码非常恶劣霸道！
 * <p>
 * 会导致当前线程永久性地睡眠，并且没有任何办法打断。
 */
public class CurrentThreadJoin {
    public static void main(String[] args) {
        final Thread mainThread = Thread.currentThread();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("------");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("------");
                mainThread.notify();
            }
        });

        t.start();
        try {
            //t.join();
            //Thread.currentThread().join(1000);
            Thread.currentThread().join();
            System.out.println("++");
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }
}
