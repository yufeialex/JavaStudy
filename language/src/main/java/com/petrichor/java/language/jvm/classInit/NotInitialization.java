package com.petrichor.java.language.jvm.classInit;

/**
 * 被动引用，类不会被初始化，有如下3种情况
 * Created by XinYufei on 2018/1/10.
 */

class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

    SuperClass() {
        System.out.println("init SuperClass");
    }
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass() {
        System.out.println("init SubClass");
    }
}

public class NotInitialization {
    public static void main(String[] args) {
        /*
         * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，
         * 只会触发父类的初始化而不会触发子类的初始化
         */
//        System.out.println(SubClass.value);

        /*
         * 创建对象数组不会使对象的类初始化。注：会使数组类初始化，在这个例子中，数组类是"[L包名.SuperClass"
         */
//        SuperClass[] array = new SuperClass[10];

        /*
        调用对象常量不会触发类初始化。书中解释：在编译阶段通过常量传播优化，已经将此常量的值“123”存储到了InitTest类的常量池中，
        以后InitTest对常量ConstClass.value的引用实际都转换为InitTest对自身常量池的引用。
         */
        System.out.println(ConstClass.value);
    }
}

class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final int value = 123;

    ConstClass() {
        System.out.println("init ConstClass");
    }
}