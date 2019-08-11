package com.yufei.languagebasic.collection;

/**
 * Created by XinYufei on 2018/1/3.
 */
class Person1 implements Comparable<Person1> {
    private int age;
    private String name;

    public Person1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person1 o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }
}
