/*
  Meituan.com Inc.
  Copyright (c) 2010-2018 All Rights Reserved.
 */
package com.petrichor.java.language.zzmJVM.chapter2;

/*
  <p></p>

  @author RefTest
 * @version $Id: AA.java, v 0.1 2018-06-10 下午1:17 @xinyufei $$
 */

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=这里自己制定路径，不加本参数，默认是在jvm 运行环境目录。
 *
 * @author zzm
 */
public class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
