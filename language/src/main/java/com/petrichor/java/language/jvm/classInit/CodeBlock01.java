package com.petrichor.java.language.jvm.classInit;

/**
 * 普通代码块：在方法或语句中出现的{}就称为普通代码块。普通代码块和一般的语句执行顺序由他们在代码中出现的次序决定--“先出现先执行”
 * Created by XinYufei on 2018/1/10.
 */
public class CodeBlock01 {
    public static void main(String[] args) {
        {
            int x = 3;
            System.out.println("1,普通代码块内的变量x=" + x);
        }
        int x = 1;
        System.out.println("主方法内的变量x=" + x);

        {
            int y = 7;
            System.out.println("2,普通代码块内的变量y=" + y);
        }
    }
}

