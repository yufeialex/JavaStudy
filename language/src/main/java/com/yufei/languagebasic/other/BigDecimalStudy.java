package com.yufei.languagebasic.other;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BigDecimalStudy {
    public static void main(String[] args) {
        BigDecimalStudy b = new BigDecimalStudy();
        b.compare0();
    }

    //浮点类型和0比较，还是用bigDecimal比较好
    Map compare0() {
        BigDecimal b0 = new BigDecimal(0);
        BigDecimal myBig = new BigDecimal(0.1);
        if (b0.compareTo(myBig) == 0) {
            System.out.println("do work");
        }
        //Java格式化数字
        DecimalFormat format = new DecimalFormat("0.00%");
        Float l = 1 / 3F;
        System.out.println(l);
        System.out.println(format.format(l));

        // 匿名map对象
        return new HashMap<String, Object>() {
            {
                put("a", "b");
            }
        };

    }


}
