package com.yufei.languagebasic.concurrent.other;

/**
 * 类比dirtyRead文件。那里面的账号信息是共享的。一个人修改，别人都受到影响。
 * 用ThreadLocal，那么账号就是独享的，自己可以修改自己的账号。
 */
public class ThreadLocalStudy {

   /* private String name;
    public void setTh(String value) {
        this.name=value;
    }

    public void getTh() {
        System.out.println(Thread.currentThread().getName() + "线程:" + this.name);
    }

    public void byBus() {
        System.out.println(Thread.currentThread().getName() + "线程:" +"用" + name + "这个名字坐车");
    }

    public void bySubway() {
        System.out.println(Thread.currentThread().getName() + "线程:" +"用" + name + "这个名字坐地铁");
    }*/


    /*private static ThreadLocal<String> name = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "default";
        }
    };*/

    private static ThreadLocal<String> name = ThreadLocal.withInitial(() -> "default");

    private void setTh(String value) {
        name.set(value);
    }

    private void getTh() {
        System.out.println(Thread.currentThread().getName() + "线程:" + name.get());
    }

    private void byBus() {
        System.out.println(Thread.currentThread().getName() + "线程:" + "用" + name.get() + "这个名字坐车");
    }

    private void bySubway() {
        System.out.println(Thread.currentThread().getName() + "线程:" + "用" + name.get() + "这个名字坐地铁");
    }

    public static void main(String[] args) {

        final ThreadLocalStudy ct = new ThreadLocalStudy();

        Thread t1 = new Thread(() -> {
            ct.setTh("辛雨非");
            ct.getTh();
            ct.byBus();
            ct.bySubway();
        }, "t1");

        Thread t2 = new Thread(() -> {
            ct.setTh("路婧威");
            ct.getTh();
            ct.byBus();
            ct.bySubway();
        }, "t2");

        t2.start();
        t1.start();
    }

}
