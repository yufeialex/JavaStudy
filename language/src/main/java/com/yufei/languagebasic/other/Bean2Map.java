package com.yufei.languagebasic.other;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class Bean2Map {
    public static void main(String[] args) {
        // 一个对象的属性复制给另一个对象
        // 有些属性类型是不支持的
        BeanUtils.copyProperties(a, b);


    }

    void map2Bean() {
        BeanUtils.populate(user0, (Map) o1);
        BeanUtils.populate(user1, (Map) o2);
    }

    void bean2Map() {
        BeanMap beanMap = new BeanMap(user);

    }
}
