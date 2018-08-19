package com.yufei.languagebasic.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by XinYufei on 2018/1/3.
 */
public class PhantomRefTest2 {
    private static ReferenceQueue<MyObject> phanQueue = new ReferenceQueue<>();
    private static Map<Reference<MyObject>, String> map = new HashMap<>();

    public static class MyObject {

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

    public static class CheckRefQueue implements Runnable {
        Reference<MyObject> obj = null;

        @Override
        public void run() {
            try {
                // 在这种情况下，可以销毁和本对象相关的一些资源
                obj = (Reference<MyObject>) phanQueue.remove();
                Object value = map.get(obj);
                System.out.println("clean resource:" + value);
                map.remove(obj);

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

        map.put(phanRef, "Some Resources");

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
