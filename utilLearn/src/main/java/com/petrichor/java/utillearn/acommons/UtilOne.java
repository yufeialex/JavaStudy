package com.petrichor.java.utillearn.acommons;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author xinyufei
 * @date 2019/12/11
 */
public class UtilOne {
    public static final DateTimeFormatter FORMAT_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter FORMAT_YMDHMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat MINUTE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
    public static SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH");
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

        System.out.println(StringUtils.isBlank(""));
        System.out.println(StringUtils.isNotBlank(""));

        ArrayList<String> raw = new ArrayList<>();
        raw.add("a");
        raw.add("b");
        System.out.println(StringUtils.join(raw, "?"));

        System.out.println(FORMAT_YMDHMS.format(LocalDateTime.now()));
    }
}
