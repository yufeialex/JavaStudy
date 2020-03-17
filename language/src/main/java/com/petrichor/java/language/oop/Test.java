package com.petrichor.java.language.oop;

/**
 * java多态性: 方法调用的优先问题
 * <p>
 * 优先级由高到低依次为：this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。让我们来看看它是怎么工作的。
 * 比如④，a2.show(b)，a2是一个引用变量，类型为A，则this为a2，b是B的一个实例，于是它到类A里面找show(B obj)方法，没有找到，于是到A的super(超类)找，而A没有超类，因此转到第三优先级this.show((super)O)，this仍然是a2，这里O为B，(super)O即(super)B即A，
 * 因此它到类A里面找show(A obj)的方法，类A有这个方法，但是由于a2引用的是类B的一个对象，B覆盖了A的show(A obj)方法，因此最终锁定到类B的show(A obj)，输出为"B and A”。
 * 再比如⑧，b.show(c)，b是一个引用变量，类型为B，则this为b，c是C的一个实例，于是它到类B找show(C obj)方法，没有找到，转而到B的超类A里面找，A里面也没有，因此也转到第三优先级this.show((super)O)，this为b，O为C，(super)O即(super)C即B，因此它到B里面找show(B obj)方法，
 * 找到了，由于b引用的是类B的一个对象，因此直接锁定到类B的show(B obj)，输出为"B and B”。
 * 按照上面的方法，可以正确得到其他的结果。
 * 问题还要继续，现在我们再来看上面的分析过程是怎么体现出蓝色字体那句话的内涵的。它说：当超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法。还是拿a2.show(b)来说吧。
 * <p>
 * a2是一个引用变量，类型为A，它引用的是B的一个对象，因此这句话的意思是由B来决定调用的是哪个方法。因此应该调用B的show(B obj)从而输出"B and B”才对。
 * 但是为什么跟前面的分析得到的结果不相符呢？！问题在于我们不要忽略了蓝色字体的后半部分，那里特别指明：这个被调用的方法必须是在超类中定义过的，也就是被子类覆盖的方法。
 * B里面的show(B obj)在超类A中有定义吗？没有！那就更谈不上被覆盖了。实际上这句话隐藏了一条信息：它仍然是按照方法调用的优先级来确定的。它在类A中找到了show(A obj)，
 * 如果子类B没有覆盖show(A obj)方法，那么它就调用A的show(A obj)（由于B继承A，虽然没有覆盖这个方法，但从超类A那里继承了这个方法，从某种意义上说，还是由B确定调用的方法，只是方法是在A中实现而已）；
 * 现在子类B覆盖了show(A obj)，因此它最终锁定到B的show(A obj)。这就是那句话的意义所在。
 * <p>
 * <p>
 * 1. 优先级是如何确定的？这事从编译期和运行期这两个方面来理解就对了，先是编译期的静态分派然后是运行期的动态分派比较清楚，首先编译的时a1和a2都是声明为A类型然后调用A的show(b),b是B类型的参数，在A中没有最精确的方法重载，只有参数类型D和A，因为B向上转型可以是A所以相对精确的重载就是A中的show(A),从而在编译期就会暂时定位到调用A的show(A obj)，然后在实际运行时，因为B是A子类且A的show(A obj)被B重写所以最后运行结果就是调用类B的show(A obj)。
 *
 * @author xinyufei
 * @date 2019/12/22
 */

public class Test {

    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();
        System.out.println("1--" + a1.show(b));
        System.out.println("2--" + a1.show(c));
        System.out.println("3--" + a1.show(d));
        System.out.println("4--" + a2.show(b)); //不是"B and B”
        System.out.println("5--" + a2.show(c)); //不是"B and B”
        System.out.println("6--" + a2.show(d));
        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));
    }


}

class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    }
}

class B extends A {
    public String show(B obj) {
        return ("B and B");
    }

    @Override
    public String show(A obj) {
        return ("B and A");
    }
}

class C extends B {
}

class D extends B {

}
