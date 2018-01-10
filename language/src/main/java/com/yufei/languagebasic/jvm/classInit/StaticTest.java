package com.yufei.languagebasic.jvm.classInit;

/**
 * Java中赋值顺序：
 * 1. 父类的静态变量赋值
 * 2. 自身的静态变量赋值
 * 3. 父类成员变量赋值和父类块赋值
 * 4. 父类构造函数赋值
 * 5. 自身成员变量赋值和自身块赋值
 * 6. 自身构造函数赋值
 * Created by XinYufei on 2018/1/10.
 *
 * 在嵌套初始化时有一个特别的逻辑。特别是内嵌的这个变量恰好是个静态成员，而且是本类的实例。
   这会导致一个有趣的现象：“实例初始化竟然出现在静态初始化之前”。
   其实并没有提前，你要知道java记录初始化与否的时机。
 */
public class StaticTest {
    public static void main(String[] args) {
        staticFunction();
    }

    // 类的初始化阶段需要做是执行类构造器（类构造器是编译器收集所有静态语句块和类变量的赋值语句按语句在源码中的顺序合并生成类构造器，
    // 对象的构造方法是<init>()，类的构造方法是<clinit>()，可以在堆栈信息中看到）
    // 此时会进行对象的初始化，对象的初始化是先初始化成员变量再执行构造方法，因此设置a为110->打印2->执行构造方法(打印3,此时a已经赋值为110，
    // 但是b只是设置了默认值0，并未完成赋值动作)，等对象的初始化完成后继续执行之前的类构造器的语句
    static StaticTest st = new StaticTest();

    static {
        System.out.println("1");
    }

    {
        System.out.println("2");
    }

    StaticTest() {
        System.out.println("3");
        System.out.println("a=" + a + ",b=" + b);
    }

    public static void staticFunction() {
        System.out.println("4");
    }

    int a = 110;
    // 类的准备阶段需要做是为类变量分配内存并设置默认值，因此类变量st为null、b为0
    static int b = 112;
    // 类变量是final，编译时javac将会为value生成ConstantValue属性，
    // 在准备阶段虚拟机就会根据ConstantValue的设置将变量设置为指定的值，那么在准备阶段b的值就是112，而不再是0了
//    static final int b = 112;
}
