package com.yufei.languagebasic.designpattern.singleton;

/**
 * Created by XinYufei on 2017/2/24.
 */
public class Singleton {

    public static void main(String[] args) {
        /*S1 instance = S1.getInstance();
        instance.setName("yufei");
        S1 instance1 = S1.getInstance();
        instance1.setName("notYufei");
        System.out.println(instance1.getName());

        Thread t1 = new Thread(() -> {
            S3 instance2 = S3.getInstance();
            instance2.setName(Thread.currentThread().getName());
            System.out.println(instance2);
            System.out.println(instance2.getName());
        }, "t1");

        Thread t2 = new Thread(() -> {
            S3 instance2 = S3.getInstance();
            instance2.setName(Thread.currentThread().getName());
//            用S3 double check的单例模式的时候，注释掉上面，因为两个线程操作的是同一个对象，所以打印结果完全一样。
//            如果用S2就不行，非线程安全。如果打开上面这句话，因为name并没有做同步，所以即使是同一个对象，也会不一致，

            System.out.println(instance2);
            System.out.println(instance2.getName());
        }, "t2");*/

//        Thread t1 = new Thread(() -> {
//                S2 instance2 = S2.getInstance();
//                System.out.println(instance2);
//        }, "t1");
//        Thread t2 = new Thread(() -> {
//            S2 instance2 = S2.getInstance();
//            System.out.println(instance2);
//        }, "t2");

 /*       t1.start();
        t2.start();*/
    }
}



// 懒汉
class EagerSingleton {
    // jvm保证在任何线程访问uniqueInstance静态变量之前一定先创建了此实例
    private static final EagerSingleton uniqueInstance = new EagerSingleton(); // 线程安全，一开始就载入，比较浪费资源
    private String name;

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    //构造函数私有化
    private EagerSingleton(){}

    //提供一个全局的静态方法
    public static EagerSingleton getInstance() {
        return uniqueInstance;
    }
}

// 饿汉
class LazySingleton {
    private static LazySingleton uniqueInstance;
    private String name;

    private LazySingleton() {
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    //提供一个全局的静态方法
    //懒汉式，多线程不能保证单例
    public static LazySingleton getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new LazySingleton();
        }
        return uniqueInstance;
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
        if(uniqueInstance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) { // 可能前两个线程都进来了，所以要再判断一遍；这里只会执行一遍；
                                      // 锁的粒度比较小；以后亦不会再用到这个锁了
                if(uniqueInstance == null) {
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
class LazyInitHolderSingleton  {
    private static class SingletonHolder  {
        private static final LazyInitHolderSingleton  INSTANCE  = new LazyInitHolderSingleton ();
    }

    public static LazyInitHolderSingleton getInstance() {
        return SingletonHolder.INSTANCE ;
    }
}

enum SingletonClass
{
    INSTANCE;
}

