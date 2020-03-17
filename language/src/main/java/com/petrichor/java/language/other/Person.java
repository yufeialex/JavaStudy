package com.petrichor.java.language.other;

/**
 * 类变量和实例变量的区别
 *
 * @author xinyufei
 * @date 2019/12/22
 */
class Person {
    String name;//成员变量，实例变量。随着对象的创建而存在于堆内存中
    static String country = "CN";//静态的成员变量，类变量。随着类的加载而存在于方法区中

    public static void show() {
        System.out.println("::::");
        //this.haha();//静态方法中不能出现this关键字
    }

    public void haha() {
        System.out.println("hahaha...");
    }
}

class StaticDemo {
    public static void main(String[] args) {

        Person p = new Person();
        p.haha(); //对象调用成员方法
        Person.show();//类名调用静态方法，也可对象调用静态方法（不推荐）
    }
}

