package com.petrichor.java.language.concurrent.synchronize;

/**
 * volatile的使用场景
 * <p>
 * 状态标志(开关模式)
 * 双重检查锁定
 * 需要利用顺序性
 */
public class VolatileThread extends Thread {

    private boolean isRunning = true;

    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        System.out.println("进入run方法..");
        int i = 0;
        while (isRunning) { // 如果不加volatile，退不出来；因为在自己线程内的变量还是真的，不管你在主线程如何修改
            //..
            /**
             * 如果不加这段代码，线程一直循环，占据CPU，导致无法去主内存刷新变量
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
