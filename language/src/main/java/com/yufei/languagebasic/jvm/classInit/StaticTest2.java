package com.yufei.languagebasic.jvm.classInit;

/**
 *
 首先在执行此段代码时，首先由main方法的调用触发静态初始化。
 在初始化Test 类的静态部分时，遇到st这个成员。
 但凑巧这个变量引用的是本类的实例。
 那么问题来了，此时静态初始化过程还没完成就要初始化实例部分了。是这样么？
 从人的角度是的。但从java的角度，一旦开始初始化静态部分，无论是否完成，后续都不会再重新触发静态初始化流程了。
 因此在实例化st变量时，实际上是把实例初始化嵌入到了静态初始化流程中。这就导致了实例初始化完全至于静态初始化之前。
 最后再考虑到文本顺序，结果就显而易见了。

 * Created by XinYufei on 2018/1/10.
 */
public class StaticTest2 {
    public static void main(String[] args) {
        func();
    }
    static StaticTest2 st = new StaticTest2();
    static void func(){}
}
