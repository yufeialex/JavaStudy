package com.yufei.languagebasic.jvm.classInit;

/**
 * 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过其子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化
 * 对于静态方法，只有直接定义这个方法的类才会被初始化，因此通过其子类来引用父类中定义的静态方法，只会触发父类的初始化而不会触发子类的初始化
 * Created by XinYufei on 2018/1/10.
 */
class SSClass {
    static {
        System.out.println("SSClass");
    }
}

class SuperClass extends SSClass {
    static {
        System.out.println("SuperClass init!");
    }

    public static void pit() {
        System.out.println("SuperClass static func!");
    }

    public static int value = 123;

    public SuperClass() {
        System.out.println("init SuperClass");
    }
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }

    // 加了这个方法，就覆盖了父类的方法；就会初始化子类
    public static void pit() {
        System.out.println("SubClass static func!");
    }

    static int a;

    public SubClass() {
        System.out.println("init SubClass");
    }
}

public class NotInitialization {
    public static void main(String[] args) {
//        System.out.println(SubClass.value);
        SubClass.pit();
    }
}