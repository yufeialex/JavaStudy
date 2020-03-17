package com.petrichor.java.utillearn.acommons;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author xinyufei
 * @date 2020/3/16
 */
public class StringUtilDemo {

    public static void main(String[] args) {

        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isNotBlank(""));

        ArrayList<String> raw = new ArrayList<>();
        raw.add("a");
        raw.add("b");
        System.out.println(StringUtils.join(raw, "?"));
    }
}
