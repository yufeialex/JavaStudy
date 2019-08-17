package com.petrichor.java.language.other;

public interface InterfaceTest {
    // 默认是public static final
    // static的是全局的，装载的时候就确定了，每次都会去静态区（方法区，永久代）取值，不会再进行赋值。
    // static final，基本上就是只有一个备份，但是不能修改。
    // Java.lang包不需要引入，比如String这个类
    String testFiled = "aa";

    // 默认是public abstract
    void testFunc();

    // 默认static
    class Innn {

    }
}
