package com.petrichor.java.language.oop;

/**
 * 父子类方法、字段的关系
 * <p>
 * 基类对派生类一无所知
 *
 * @author xinyufei
 * @date 2019/12/21
 */
class X {
    int a;

    public void setA(int a) {
        this.a = a;
        System.out.println("use a");
    }

    public void notOverride() {
        System.out.println("notOverride method");
    }

    public int getA() {
        return a;
    }
}

class Y extends X {
    private int a;

    @Override
    public void setA(int a) {
//        super.a = a;
        this.a = a;
        System.out.println("use b");
    }

    public int uniqueInB() {
        return a;
    }

    public static void main(String[] args) {
        /*
         * e是A的实例，能看到的都是A内定义的方法和字段
         */
        X e = new X();
        e.setA(5);
        System.out.println(e.getA());
        e.notOverride();
        System.out.println();

        /*
        c是B的实例，但是却被A对象的模型所束缚，所以它
        能看到的只是A内定义的方法和字段，
        但是如果B中重写了某个方法，那么执行的时候执行的是B中重写的方法
        看不到B中独有的方法和字段，哪怕是重名的
         */
        X c = new Y();
        c.setA(5);                    //重写的方法，用子类的
        System.out.println(c.getA()); //没重写的方法，用父类的，操作的对象也是父类的
        c.notOverride();              //没重写的方法，用父类的
        System.out.println();
//        System.out.println(c.getValue()); //子类独有的方法，看不到

        /*
        d是B的实例，能用父类定义的方法，也能看到父类的字段，但是
        如果方法重写了，就用自己的方法。
        如果字段重名了，用的时候要区分是super.a还是this.a。不指出时默认是this.a
        如果方法没有重名，但是有重名字段，那么父类继承的这个方法操作的就是父类的字段，不是子类的字段，因为父类方法中的this等于子类中的super
         */
        Y d = new Y();
        d.setA(5);
        System.out.println(d.getA()); // 对于d来说，这个方法里调用的是super.a
        System.out.println(d.uniqueInB()); // 这个方法里调用的是this.a
    }

}
