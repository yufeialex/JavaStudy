package com.petrichor.java.language.concurrent.design.design014;

public class ProxyFuture implements Future {

    private RealFuture realData;

    private boolean isReady = false;

    public synchronized void setRealData(RealFuture realData) {
        //如果已经装载完毕了，就直接返回
        if (isReady) {
            return;
        }
        //如果没装载，进行装载真实对象
        this.realData = realData;
        isReady = true;
        //进行通知
        notify();
    }

    @Override
    public synchronized String get() {
        System.out.println("现在需要使用真实数据了！");
        //如果没装载好 程序就一直处于阻塞状态
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //装载好直接获取数据即可
        return this.realData.get();
    }


}
