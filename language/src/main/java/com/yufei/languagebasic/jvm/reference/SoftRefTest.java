package com.yufei.languagebasic.jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 *
 * 用来描述一些还有用但并非必须的对象。
 *
 * 对于软引用关联着的对象，如果内存充足，则GC不会回收该对象，如果内存不够了，就会回收这些对象的内存。
 *
 * 软引用可用来实现内存敏感的高速缓存。软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被GC回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
 *
 * 运行参数：-Xmx5M -XX:+PrintGCDetails
 *
 * 首先指定程序运行的堆空间，然后构造RefTest对象，并将其赋值给object变量，构成强引用。
 * 然后使用SoftReference构造这个RefTest对象的软引用softRef，并注册到softQueue引用队列。
 * 注意：当softRef被回收时，会被加入softQueue队列。这个机制是为了干啥？？
 * 设置obj=null，删除这个强引用，因此，系统内对RefTest对象的引用只剩下软引用。
 * 此时，显示调用GC，在打印语句中通过软引用的get()方法去取得RefTest对象的引用，如果GC在内存充足的情况下，不会回收软引用对象。
 *
 * 接着，请求一块大的堆空间，目的是使系统堆内存使用紧张，从而产生新一轮的隐式GC。
 * 在这次GC后，softRef.get()不再返回RefTest对象，而是返回null，说明在系统内存紧张的情况下，软引用被回收。软引用被回收时，会被加入注册的引用队列。
 * 如果将上面案例中的数组再改大点，比如5*1024*1024，就会抛出OOM异常
 *
 * Created by XinYufei on 2018/1/3.
 */
public class SoftRefTest {

    private static ReferenceQueue<RefTest> softQueue = new ReferenceQueue<>();

    public static class RefTest {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("RefTest's finalize called");
        }

        @Override
        public String toString() {
            return "I am RefTest";
        }
    }

    public static class CheckRefQueue implements Runnable {
        Reference<RefTest> softRef = null;

        @Override
        public void run() {
            try {
                // 没有对象的时候会阻塞
                softRef = (Reference<RefTest>) softQueue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (softRef != null) {
                // 如果能执行到这里，软引用指向的对象已经被回收了
                System.out.println("Object for SoftReference is " + softRef.get());
            }
        }
    }

    public static void main(String[] args) {
        RefTest object = new RefTest();
        SoftReference<RefTest> softRef = new SoftReference<>(object, softQueue);
        new Thread(new CheckRefQueue()).start();

        object = null;    //删除强引用
        System.gc();
        // 一般gc不会回收软引用对象
        System.out.println("After GC: softRef.get result = " + softRef.get());
        System.out.println("allocate big memory ");
        // 数组大小要根据不同的环境进行配置才出现特定结果
        // 如果内存充足，直接分配
        // 如果空间不足，就会回收软引用指向的对象
        // 如果回收了还不够，就OOM
        byte[] b = new byte[5 * 1024 * 718];
        System.out.println("After memory allocation: softRef.get result = " + softRef.get());
        System.gc();
    }
}
