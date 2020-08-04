package com.study.javanewspecial.java8.demo_date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * todo
 *
 * @author ldb
 * @date 2020/07/15
 */
public class Test_Date {

    public static void main(String[] args) {

        //testInstant();
        System.out.println("---------------------");
        //testLocalDate();
        System.out.println("---------------------");
        //testLocalTime();
        System.out.println("---------------------");
        //testLocalDateTime();
        System.out.println("---------------------");
        testZonedDateTime();
        System.out.println("---------------------");
        testDateTimeFormater();
        System.out.println("---------------------");
        diffTime();

    }

    public static void testInstant(){
        /**
         * Instant 和 Date 一样，表示一个时间戳，用于描述一个时刻，只不过它较 Date 而言，可以描述更加精确的时刻。并且 Instant 是时区无关的。
         * Date 最多可以表示毫秒级别的时刻，而 Instant 可以表示纳秒级别的时刻。
         * public static Instant now()：根据系统当前时间创建一个 Instant 实例，表示当前时刻
         * public static Instant ofEpochSecond(long epochSecond)：通过传入一个标准时间的偏移值来构建一个 Instant 实例
         * public static Instant ofEpochMilli(long epochMilli)：通过毫秒数值直接构建一个 Instant 实例
         */
        //创建 Instant 实例
        Instant instant = Instant.now();
        System.out.println(instant);

        Instant instant1 = Instant.ofEpochSecond(20);
        System.out.println(instant1);

        Instant instant2 = Instant.ofEpochSecond(30,100);
        System.out.println(instant2);

        Instant instant3 = Instant.ofEpochMilli(1000);
        System.out.println(instant3);
    }

    public static void testLocalDate(){
        /**
         * 不同于 Calendar 既能处理日期又能处理时间，java.time 的新式 API 分离开日期和时间，用单独的类进行处理。LocalDate 专注于处理日期相关信息。
         *
         * LocalDate 依然是一个不可变类，它关注时间中年月日部分，我们可以通过以下的方法构建和初始化一个 LocalDate 实例：
         * public static LocalDate now()：截断当前系统时间的年月日信息并初始化一个实例对象
         * public static LocalDate of(int year, int month, int dayOfMonth)：显式指定年月日信息
         * public static LocalDate ofYearDay(int year, int dayOfYear)：根据 dayOfYear 可以推出 month 和 dayOfMonth
         * public static LocalDate ofEpochDay(long epochDay)：相对于格林零时区时间的日偏移量
         */
        //构建 LocalDate 实例
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalDate localDate1 = LocalDate.of(2017,7,22);
        System.out.println(localDate1);

        LocalDate localDate2 = LocalDate.ofYearDay(2018,100);
        System.out.println(localDate2);

        LocalDate localDate3 = LocalDate.ofEpochDay(10);
        System.out.println(localDate3);
    }

    public static void testLocalTime(){
        /**
         * public static LocalTime now()：根据系统当前时刻获取其中的时间部分内容
         * public static LocalTime of(int hour, int minute)：显式传入小时和分钟来构建一个实例对象
         * public static LocalTime of(int hour, int minute, int second)：通过传入时分秒构造实例
         * public static LocalTime of(int hour, int minute, int second, int nanoOfSecond)：传入时分秒和毫微秒构建一个实例
         * public static LocalTime ofSecondOfDay(long secondOfDay)：传入一个长整型数值代表当前日已经过去的秒数
         * public static LocalTime ofNanoOfDay(long nanoOfDay)：传入一个长整型代表当前日已经过去的毫微秒数
         */
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalTime localTime1 = LocalTime.of(23,59);
        System.out.println(localTime1);

        LocalTime localTime2 = LocalTime.ofSecondOfDay(10);
        System.out.println(localTime2);
    }

    public static void testLocalDateTime(){

    }

    /**
     * 无论是我们的 LocalDate，或是 LocalTime，甚至是 LocalDateTime，它们基本是时区无关的，
     * 内部并没有存储时区属性，而基本用的系统默认时区。往往有些场景之下，缺乏一定的灵活性。
     *
     * ZonedDateTime 可以被理解为 LocalDateTime 的外层封装，它的内部存储了一个 LocalDateTime 的实例，专门用于普通的日期时间处理。
     * 此外，它还定义了 ZoneId 和 ZoneOffset 来描述时区的概念。
     *
     * ZonedDateTime 和 LocalDateTime 的一个很大的不同点在于，后者内部并没有存储时区，所以对于系统的依赖性很强，
     * 往往换一个时区可能就会导致程序中的日期时间不一致。
     *
     * public static ZonedDateTime now()：系统将以默认时区计算并存储日期时间信息
     * public static ZonedDateTime now(ZoneId zone)：指定时区
     * public static ZonedDateTime of(LocalDate date, LocalTime time, ZoneId zone)：指定日期时间和时区
     * public static ZonedDateTime of(LocalDateTime localDateTime, ZoneId zone)
     * public static ZonedDateTime ofInstant(Instant instant, ZoneId zone)：通过时刻和时区构建实例对象
     */
    public static void testZonedDateTime(){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(localDateTime,zoneId);
        System.out.println(zonedDateTime1);

        Instant instant = Instant.now();
        ZoneId zoneId1 = ZoneId.of("GMT");
        ZonedDateTime zonedDateTime2 = ZonedDateTime.ofInstant(instant,zoneId1);
        System.out.println(zonedDateTime2);

        String dateStr = "2020-07-15T15:34:56.512+08:00";
        ZonedDateTime date1 = ZonedDateTime.parse(dateStr);
        System.out.println(date1);
    }

    public static void testDateTimeFormater(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(formatter.format(localDateTime));

        String str = "2008年08月23日 23:59:59";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        LocalDateTime localDateTime2 = LocalDateTime.parse(str,formatter2);
        System.out.println(localDateTime2);
    }

    /**
     * Period：处理两个日期之间的差值
     * Duration：处理两个时间之间的差值
     */
    public static void diffTime(){
        LocalDate date = LocalDate.of(2017,7,22);
        LocalDate date1 = LocalDate.now();
        Period period = Period.between(date,date1);
        System.out.println(period.getYears() + "年" +
                period.getMonths() + "月" +
                period.getDays() + "天");

        LocalTime time = LocalTime.of(20,30);
        LocalTime time1 = LocalTime.of(23,59);
        Duration duration = Duration.between(time,time1);
        System.out.println(duration.toMinutes() + "分钟");

        LocalDateTime my1 = LocalDateTime.of(2020,4,21,10,8,4);
        LocalDateTime my2 = LocalDateTime.now();

    }
}
