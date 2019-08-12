package com.petrichor.languagebasic.java8;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Sort {
    public static void main(String[] args) {
        List<Map> all = new ArrayList<>();
        all.sort((a, b) -> {
            Timestamp createTime = (Timestamp) a.get("createTime");
            Timestamp createTime2 = (Timestamp) b.get("createTime");
            if (createTime.after(createTime2)) {
                return 1;
            }
            if (createTime.before(createTime2)) {
                return -1;
            }
            return 0;
        });
    }
}
