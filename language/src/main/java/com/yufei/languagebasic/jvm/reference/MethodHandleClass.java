package com.yufei.languagebasic.jvm.reference;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by XinYufei on 2018/1/30.
 */
class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }

    //类静态字段
    public static int value = 123;
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}

class ConstantsClass {
    static {
        System.out.println("ConstantsClass init!");
    }

    //类常量
    public static final int VALUE = 123;
}

public class MethodHandleClass {
    static {
        System.out.println("MethodHandleClass init");
    }

    public static void testMethodHandle(String str) {
        System.out.println(str);
    }
}

// 输出结果为 ：SuperClass init!
class Initialization1 {
    public static void main(String[] args) {
// 遇到new字节码指令时，如果类没有进行过初始化，则需要先触发类的初始化
        new SuperClass();
    }
}

// 输出结果为 ：SuperClass init!
class Initialization2 {
    public static void main(String[] args) {
        try {
// 对类进行反射调用的时候，如果类没有进行过初始化，则需要先触发其初始化
            Class.forName("com.yufei.languagebasic.jvm.reference.SuperClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// 输出结果为
//    SuperClass init!
//    SubClass init!
class Initialization3 {
    public static void main(String[] args) {
// 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要触发其父类的初始化
        new SubClass();
    }
}

// 输出结果为
//当虚拟机启动时，用户需要指定一个要执行的主类，虚拟机会先初始化这个主类
class Initialization4 {
    static {
        System.out.println("当虚拟机启动时，用户需要指定一个要执行的主类，虚拟机会先初始化这个主类");
    }

    public static void main(String[] args) {

    }
}

// 输出结果为
// MethodHandleClass init
// 当使用JDK 1.7 的动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的结果是
// REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，并且这个方法句柄对应的类没有进行过初始化，则需要先触发其初始化
class Initialization5 {
    public static void main(String[] args) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        try {
            MethodHandle testMethodHandle = lookup.findStatic(MethodHandleClass.class, "testMethodHandle", MethodType.methodType(void.class, String.class));
            try {
                testMethodHandle.invoke("当使用JDK 1.7 的动态语言支持时，如果一个java.lang.invoke.MethodHandle实例最后的结果是\n" +
                        "REF_getStatic、REF_putStatic、REF_invokeStatic的方法句柄，并且这个方法句柄对应的类没有进行过初始化，则需要先触发其初始化");
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

// 输出结果为
//  SuperClass init!
//  123
class NotInitialization1 {
    public static void main(String[] args) {
        // 子类引用父类的静态字段，不会导致子类初始化
        System.out.println(SubClass.value);
    }
}

// 输出结果为无
class NotInitialization2 {
    public static void main(String[] args) {
        // 注意，这里的字节码指令是newarray而非new，那么这段代码的初始化就是类[SuperClass的初始化
        SuperClass[] sca = new SuperClass[10];
    }
}


// 定义常量的类的初始化
// 输出结果为 123
class NotInitialization3 {
    public static void main(String[] args) {
// 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，
// 因此不会触发定义常量的类的初始化
        System.out.println(ConstantsClass.VALUE);
    }
}













