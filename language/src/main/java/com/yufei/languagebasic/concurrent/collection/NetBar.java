package com.yufei.languagebasic.concurrent.collection;

import java.util.concurrent.DelayQueue;

public class NetBar implements Runnable {

    private DelayQueue<User> queue = new DelayQueue<>();

    public boolean yinye = true;

    public void shangji(String name, String id, int money) {
        User man = new User(name, id, 1000 * money + System.currentTimeMillis());
        System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "交钱" + money + "块,开始上机...");
        this.queue.add(man);
    }

    public void xiaji(User man) {
        System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "时间到下机...");
    }

    @Override
    public void run() {
        while (yinye) {
            try {
                User man = queue.take();
                xiaji(man);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        try {
            System.out.println("网吧开始营业");
            NetBar siyu = new NetBar();
            Thread shangwang = new Thread(siyu);
            shangwang.start();

            siyu.shangji("路人甲", "123", 1);
            siyu.shangji("路人乙", "234", 10);
            siyu.shangji("路人丙", "345", 5);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}  