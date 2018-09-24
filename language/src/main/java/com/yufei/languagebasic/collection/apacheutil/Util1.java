package com.yufei.languagebasic.collection.apacheutil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class Util1 {
    public static void main(String[] args) {
        CollectionUtils.isEmpty(null);
        StringUtils.isEmpty("aa");
    }
}
