package com.petrichor.java.language.other;

class ClassTest {

    /**
     * 1. 第一就是说明你已经知道这个方法提供的功能已经满足你要求，不需要进行扩展，并且也不允许任何从此类继承的类来覆写这个方法，
     * 但是继承仍然可以继承这个方法，也就是说可以直接使用。
     * 2. 第二就是允许编译器将所有对此方法的调用转化为inline调用的机制，它会使你在调用final方法时，直接将方法主体插入到调用处，
     * 而不是进行例行的方法调用，例如保存断点，压栈等，这样可能会使你的程序效率有所提高，然而当你的方法主体非常庞大时，
     * 或你在多处调用此方法，那么你的调用主体代码便会迅速膨胀，可能反而会影响效率，所以你要慎用final进行方法定义。
     */
    public static final int test1() {
        // 方法调用完，参数作为局部变量就没有了。Return临时存储的那部分也没了。
//        循环里面是类对象的，要再里面new这个对象，否则会反复用一个对象
        return 0;
    }
    // static方法没必要重写，因为实现不了多态。所以没必要加final。
    // 但是我觉得，加了final能有语法检查

}

class T2 extends ClassTest {
//    public static final int test1() {
//        return 0;
//    }
}