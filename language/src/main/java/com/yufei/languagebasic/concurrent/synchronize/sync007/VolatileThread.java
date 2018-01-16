package com.yufei.languagebasic.concurrent.synchronize.sync007;

public class VolatileThread extends Thread {

    private volatile boolean isRunning = true;

    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        System.out.println("进入run方法..");
        int i = 0;
        while (isRunning) { // 如果不加volatile，退不出来；因为在自己线程内的变量还是真的，不管你在主线程如何修改
            //..
        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileThread rt = new VolatileThread();
        rt.start();
        Thread.sleep(1000);
        rt.setRunning(false); // 这里是在主线程修改变量
        System.out.println("isRunning的值已经被设置了false");
    }


}
