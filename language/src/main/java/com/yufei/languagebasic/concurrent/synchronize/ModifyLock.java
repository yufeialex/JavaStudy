package com.yufei.languagebasic.concurrent.synchronize;

/**
 * 同一对象属性的修改不会影响锁的情况
 */
public class ModifyLock {

    private String name;
    private int age;

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private synchronized void changeAttribute(String name, int age) {
        try {
            System.out.println("当前线程 : " + Thread.currentThread().getName() + " 开始");
            this.setName(name);
            this.setAge(age);

            System.out.println("当前线程 : " + Thread.currentThread().getName() + " 修改对象内容为： "
                    + this.getName() + ", " + this.getAge());

            Thread.sleep(2000);
            System.out.println("当前线程 : " + Thread.currentThread().getName() + " 结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ModifyLock modifyLock = new ModifyLock();
        Thread t1 = new Thread(() -> modifyLock.changeAttribute("张三", 20), "t1");
        Thread t2 = new Thread(() -> modifyLock.changeAttribute("李四", 21), "t2");

        t1.start();
        Thread.sleep(100);
        t2.start();
    }

}
