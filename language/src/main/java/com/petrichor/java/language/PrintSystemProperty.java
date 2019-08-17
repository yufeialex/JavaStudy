/*
  Meituan.com Inc.
  Copyright (c) 2010-2018 All Rights Reserved.
 */
package com.petrichor.java.language;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * 打印Java的系统变量
 * </p>
 *
 * @author java
 * @version $Id: AA.java, v 0.1 2018-06-11 下午8:24 @xinyufei $$
 */
public class PrintSystemProperty {
    public static void main(String[] args) {
        Map<String, String> map = System.getenv();
        for (Iterator<String> itr = map.keySet().iterator(); itr.hasNext(); ) {
            String key = itr.next();
            System.out.println(key + "  =  " + map.get(key));
        }

        Properties properties = System.getProperties();
        showKeysAndValues(properties);
    }

    /**
     * @param properties
     */
    private static void showKeys(Properties properties) {
        Enumeration<?> enu = properties.propertyNames();
        while (enu.hasMoreElements()) {
            Object key = enu.nextElement();
            System.out.println(key);
        }
    }

    /**
     * @param properties
     */
    private static void showValues(Properties properties) {
        Enumeration<Object> enu = properties.elements();
        while (enu.hasMoreElements()) {
            Object value = enu.nextElement();
            System.out.println(value);
        }
    }

    /**
     * @param properties
     */
    private static void showKeysAndValues(Properties properties) {
        Iterator<Map.Entry<Object, Object>> it = properties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key   :" + key);
            System.out.println("value :" + value);
            System.out.println("---------------");
        }
    }
}
