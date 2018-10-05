package com.yufei.languagebasic.concurrent.thread;

public class StopThread implements Runnable {
    private boolean stopping = false;
    private Thread currentThread = null;

    public void run() {
        currentThread = Thread.currentThread();
        while (!isStopping()) {
            // do something, sleep a while
            try {
                Thread.sleep(1000);
                System.out.println("hh");
            } catch (InterruptedException e) {

            }
        }
        System.out.println("结束啦");
    }

    public synchronized void shutdown() {
        stopping = true;
//        currentThread.notifyAll();
    }

    public synchronized boolean isStopping() {
        return stopping;
    }

    public static void main(String[] args) throws InterruptedException {
        StopThread stopThread = new StopThread();
        new Thread(stopThread).start();
        Thread.sleep(3000);
//        System.out.println(stopThread.isStopping());
        stopThread.shutdown();
    }
}
