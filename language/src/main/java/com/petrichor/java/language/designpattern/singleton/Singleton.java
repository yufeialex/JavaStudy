package com.petrichor.java.language.designpattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by XinYufei on 2017/2/24.
 */
public class Singleton implements Runnable {

    public static void main(String[] args) {

        // 单线程情况，测试单例功能是否实现
//        DoubleCheckedLockingSingleton instance = DoubleCheckedLockingSingleton.getInstance();
//        instance.setName("yufei");
//        DoubleCheckedLockingSingleton instance1 = DoubleCheckedLockingSingleton.getInstance();
//        System.out.println(instance1.getName());

        // 多线程情况，测试线程安全问题
        ExecutorService es = Executors.newFixedThreadPool(2);
        Singleton singleton = new Singleton();
        Future<?> submit = es.submit(singleton);
        Future<?> submit2 = es.submit(singleton);
        es.shutdown();
    }

    @Override
    public void run() {
        LazySingleton instance = LazySingleton.getInstance();
        instance.setName(Thread.currentThread().getName());
        System.out.println(instance);
        System.out.println(instance.getName());
    }
}


// 饿汉
class EagerSingleton {
    // jvm保证在任何线程访问uniqueInstance静态变量之前一定先创建了此实例
    // 线程安全，一开始就载入，比较浪费资源
    private static final EagerSingleton uniqueInstance = new EagerSingleton();
    private String name;

    //构造函数私有化
    private EagerSingleton() {
    }

    //提供一个全局的静态方法
    public static EagerSingleton getInstance() {
        return uniqueInstance;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

}

// 懒汉
class LazySingleton {
    private static LazySingleton uniqueInstance;
    private String name;

    //构造函数私有化
    private LazySingleton() {
    }

    //提供一个全局的静态方法
    //多线程不能保证单例
    public static LazySingleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LazySingleton();
        }
        return uniqueInstance;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

}

// double check
class DoubleCheckedLockingSingleton {
    // java中使用双重检查锁定机制,由于Java编译器和JIT的优化的原因系统无法保证我们期望的执行次序。
    // 在java5.0修改了内存模型,使用volatile声明的变量可以强制屏蔽编译器和JIT的优化工作
    private static volatile DoubleCheckedLockingSingleton uniqueInstance;
    private String name;

    private DoubleCheckedLockingSingleton() {
    }

    // 提供一个全局的静态方法
    // 为了效率，只在创建时候加锁，以后不需要锁定
    public static DoubleCheckedLockingSingleton getInstance() {
        if (uniqueInstance == null) {
            // 锁的粒度比较小；以后亦不会再用到这个锁了
            synchronized (DoubleCheckedLockingSingleton.class) {
                // 可能前两个线程都进来了，所以要再判断一遍；这里只会执行一遍
                if (uniqueInstance == null) {
                    uniqueInstance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return uniqueInstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// inner class
class LazyInitHolderSingleton {
    private LazyInitHolderSingleton() {
    }

    private static class SingletonHolder {
        private static final LazyInitHolderSingleton INSTANCE = new LazyInitHolderSingleton();
    }

    public static LazyInitHolderSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

enum SingletonClass {
    INSTANCE;
}

