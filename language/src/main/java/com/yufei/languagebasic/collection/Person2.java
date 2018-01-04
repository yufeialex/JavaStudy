package com.yufei.languagebasic.collection;

/**
 * Created by XinYufei on 2018/1/3.
 */
public final class Person2
{
    private int age;
    private String name;

    public Person2(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return name+":"+age;
    }

}