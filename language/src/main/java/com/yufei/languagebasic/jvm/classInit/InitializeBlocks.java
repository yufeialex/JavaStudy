package com.yufei.languagebasic.jvm.classInit;

/**
 * Created by XinYufei on 2018/1/10.
 *
 * 静态代码块在类加载时被执行，而非静态代码(包括初始化代码块和构造函数)在生成对象时才被执行，
 * 故父类和子类的静态初始化代码块最早执行；
 *
 * 初始化块与构造函数的执行顺序，前者要早于后者。
 *
 * 执行顺序：（优先级从高到低。）静态代码块>main方法>构造代码块>构造方法。
 *
 * 其中静态代码块只执行一次。构造代码块在每次创建对象时都会执行。
 */

class Blocks {
    static {
        System.out.println("父类静态初始化块");
    }

    {
        System.out.println("父类初始化块");
    }

    Blocks() {
        System.out.println("父类构造函数");
    }
}

public class InitializeBlocks extends Blocks {
    static {
        System.out.println("子类静态初始化块");
    }

    {
        System.out.println("子类初始化块");
    }

    public InitializeBlocks() {
        System.out.println("子类构造函数");
    }

    public static void main(String[] args) {
        new InitializeBlocks();
    }
}

//    父类静态初始化块
//    子类静态初始化块
//    父类初始化块
//    父类构造函数
//    子类初始化块
//    子类构造函数








