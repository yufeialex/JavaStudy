package com.petrichor.java.language.dataApi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author xinyufei
 * @date 2019/12/11
 * <p>
 * 新API修正了旧API不合理的常量设计：
 * <p>
 * Month的范围用1~12表示1月到12月；
 * Week的范围用1~7表示周一到周日。
 * <p>
 * 最后，新API的类型几乎全部是不变类型（和String类似），可以放心使用不必担心被修改。
 */
public class NewDateUtilDemo {
    public static final DateTimeFormatter FORMAT_YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter FORMAT_YMDHMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        basicUse();
    }

    /**
     * ISO 8601规定的日期和时间分隔符是T。标准格式如下：
     * <p>
     * 日期：yyyy-MM-dd
     * 时间：HH:mm:ss
     * 带毫秒的时间：HH:mm:ss.SSS
     * 日期和时间：yyyy-MM-dd'T'HH:mm:ss
     * 带毫秒的日期和时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    public static void basicUse() {
        LocalDate d = LocalDate.now(); // 当前日期
        LocalTime t = LocalTime.now(); // 当前时间
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        System.out.println(d); // 严格按照ISO 8601格式打印
        System.out.println(t); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印
    }

    /**
     * 确保同一时刻
     */
    public static void sameTime() {
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        LocalDate d = dt.toLocalDate(); // 转换到当前日期
        LocalTime t = dt.toLocalTime(); // 转换到当前时间
        System.out.println(d); // 严格按照ISO 8601格式打印
        System.out.println(t); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印
    }

    /**
     * 指定日期和时间:
     */
    public static void setDateTime() {
        LocalDate d2 = LocalDate.of(2019, 11, 30); // 2019-11-30, 注意11=11月
        LocalTime t2 = LocalTime.of(15, 16, 17); // 15:16:17
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);
        LocalDateTime dt3 = LocalDateTime.of(d2, t2);
    }

    /**
     * 用标准字符串转换
     */
    public static void setCalendar() {
        LocalDateTime dt = LocalDateTime.parse("2019-11-19T15:16:17");
        LocalDate d = LocalDate.parse("2019-11-19");
        LocalTime t = LocalTime.parse("15:16:17");
    }

    public static void useDateTimeFormatter() {
        // 自定义格式化:
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));

        // 用自定义格式解析:
        LocalDateTime dt2 = LocalDateTime.parse("2019/11/30 15:16:17", dtf);
        System.out.println(dt2);
    }

    /**
     * 日期加减
     * 链式调用
     * <p>
     * 注意到月份加减会自动调整日期，例如从2019-10-31减去1个月得到的结果是2019-09-30，因为9月没有31日。
     */
    public static void datePlusMinus() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 加5天减3小时:
        LocalDateTime dt2 = dt.plusDays(5).minusHours(3);
        System.out.println(dt2); // 2019-10-31T17:30:59
        // 减1月:
        LocalDateTime dt3 = dt2.minusMonths(1);
        System.out.println(dt3); // 2019-09-30T17:30:59
    }

    /**
     * 对日期和时间进行调整则使用withXxx()方法，例如：withHour(15)会把10:11:12变为15:11:12：
     * <p>
     * 调整年：withYear()
     * 调整月：withMonth()
     * 调整日：withDayOfMonth()
     * 调整时：withHour()
     * 调整分：withMinute()
     * 调整秒：withSecond()
     */
    public static void dateSet() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 日期变为31日:
        LocalDateTime dt2 = dt.withDayOfMonth(31);
        System.out.println(dt2); // 2019-10-31T20:30:59
        // 月份变为9:
        LocalDateTime dt3 = dt2.withMonth(9);
        System.out.println(dt3); // 2019-09-30T20:30:59
    }

    /**
     * 计算某个月第1个周日
     */
    public static void calulate() {
        // 本月第一天0:00时刻:
        LocalDateTime firstDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firstDay);

        // 本月最后1天:
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

        // 下月第1天:
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);

        // 本月第1个周一:
        LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstWeekday);
    }

    /**
     * 比较时间先后
     * LocalDateTime无法与时间戳进行转换，因为LocalDateTime没有时区，无法确定某一时刻。
     * ZonedDateTime相当于LocalDateTime加时区的组合，它具有时区，可以与long表示的时间戳进行转换
     */
    public static void compare() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));
    }

}
