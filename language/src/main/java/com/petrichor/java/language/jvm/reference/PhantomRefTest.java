package com.petrichor.java.language.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * 再经过一次GC之后，系统找到了垃圾对象，并调用finalize()方法回收内存，但没有立即加入回收队列。
 * 第二次GC时，该对象真正被GC清除，此时，加入虚引用队列。
 * Created by XinYufei on 2018/1/3.
 */
public class PhantomRefTest {
    private static ReferenceQueue<MyObject> phanQueue = new ReferenceQueue<>();

    static class MyObject {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject's finalize called");
        }

        @Override
        public String toString() {
            return "I am MyObject";
        }
    }

    static class CheckRefQueue implements Runnable {
        Reference<MyObject> obj = null;

        @Override
        public void run() {
            try {
                obj = (Reference<MyObject>) phanQueue.remove();
                System.out.println("删除的虚引用为：" + obj + "  but获取虚引用的对象obj.get()=" + obj.get());
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyObject object = new MyObject();
        Reference<MyObject> phanRef = new PhantomReference<>(object, phanQueue);
        System.out.println("创建的虚引用为：" + phanRef);
        new Thread(new CheckRefQueue()).start();

        object = null;
        TimeUnit.SECONDS.sleep(1);
        int i = 1;
        while (true) {
            System.out.println("第" + i++ + "次gc");
            System.gc();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
