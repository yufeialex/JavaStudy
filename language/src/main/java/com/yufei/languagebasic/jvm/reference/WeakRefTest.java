package com.yufei.languagebasic.jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 在GC之前，弱引用对象并未被垃圾回收器发现，因此通过 weakRef.get()可以获取对应的对象引用。
 * 但是只要进行垃圾回收，弱引用一旦被发现，便会立即被回收，并加入注册引用队列中。此时再试图通过weakRef.get()获取对象的引用就会失败。
 * <p>
 * Created by XinYufei on 2018/1/3.
 */
public class WeakRefTest {
    private static ReferenceQueue<RefTest> weakQueue = new ReferenceQueue<>();

    static class RefTest {

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

    static class CheckRefQueue implements Runnable {
        Reference<RefTest> obj = null;

        @Override
        public void run() {
            try {
                obj = (Reference<RefTest>) weakQueue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                System.out.println("删除的弱引用为：" + obj + "  but获取弱引用的对象obj.get()=" + obj.get());
            }
        }
    }

    public static void main(String[] args) {
        RefTest object = new RefTest();
        Reference<RefTest> weakRef = new WeakReference<>(object, weakQueue);

        System.out.println("创建的弱引用为：" + weakRef);
        new Thread(new CheckRefQueue()).start();

        object = null;
        System.out.println("Before GC: Weak Get= " + weakRef.get());
        System.gc();
        System.out.println("After GC: Weak Get= " + weakRef.get());
    }
}
