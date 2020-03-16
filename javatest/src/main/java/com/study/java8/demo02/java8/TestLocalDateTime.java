package com.study.java8.demo02.java8;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.util.Set;

import org.junit.Test;

/**
 * Instant：时间戳
 * Duration：持续时间，时间差
 * LocalDate：只包含日期，比如：2016-10-20
 * LocalTime：只包含时间，比如：23:12:10
 * LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
 * Period：时间段
 * ZoneOffset：时区偏移量，比如：+8:00
 * ZonedDateTime：带时区的时间
 * Clock：时钟，比如获取目前美国纽约的时间
 * @author Admin
 */
public class TestLocalDateTime {
	
	//6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
	@Test
	public void test7(){
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(ldt);
		
		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
		System.out.println(zdt);
	}
	
	@Test
	public void test6(){
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}

	
	//5. DateTimeFormatter : 解析和格式化日期或时间
	@Test
	public void test5(){
//		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
		
		LocalDateTime ldt = LocalDateTime.now();
		String strDate = ldt.format(dtf);
		
		System.out.println(strDate);
		
		LocalDateTime newLdt = ldt.parse(strDate, dtf);
		System.out.println(newLdt);
	}
	
	//4. TemporalAdjuster : 时间校正器
	@Test
	public void test4(){
	LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime ldt2 = ldt.withDayOfMonth(10);
		System.out.println(ldt2);
		
		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(ldt3);
		
		//自定义：下一个工作日
		LocalDateTime ldt5 = ldt.with((l) -> {
			LocalDateTime ldt4 = (LocalDateTime) l;
			
			DayOfWeek dow = ldt4.getDayOfWeek();
			
			if(dow.equals(DayOfWeek.FRIDAY)){
				return ldt4.plusDays(3);
			}else if(dow.equals(DayOfWeek.SATURDAY)){
				return ldt4.plusDays(2);
			}else{
				return ldt4.plusDays(1);
			}
		});
		
		System.out.println(ldt5);
		
	}
	
	//3.
	//Duration : 用于计算两个“时间”间隔
	//Period : 用于计算两个“日期”间隔
	@Test
	public void test3(){
		Instant ins1 = Instant.now();
		
		System.out.println("--------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		Instant ins2 = Instant.now();
		
		System.out.println("所耗费时间为：" + Duration.between(ins1, ins2));
		
		System.out.println("----------------------------------");
		
		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.of(2011, 1, 1);
		
		Period pe = Period.between(ld2, ld1);
		System.out.println(pe.getYears());
		System.out.println(pe.getMonths());
		System.out.println(pe.getDays());
	}
	
	//2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
	@Test
	public void test2(){
		Instant ins = Instant.now();  //默认使用 UTC 时区,北京时间=UTC+8=GMT+8。
		System.out.println(ins);
		System.out.println(ins.toEpochMilli());
		System.out.println(ins.toEpochMilli());
		System.out.println(System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());
		System.out.println(ins.toEpochMilli()+"=="+System.currentTimeMillis());

		OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt);
		
		System.out.println(ins.getNano());



		Instant ins2 = Instant.ofEpochSecond(5);
		System.out.println(ins2);
	}
	
	//1. LocalDate、LocalTime、LocalDateTime

	/**
	 *  LocalDate：只包含日期，比如：2016-10-20
	 *  LocalTime：只包含时间，比如：23:12:10
	 *  LocalDateTime：包含日期和时间，比如：2016-10-20 23:14:21
	 */
	@Test
	public void test1(){
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		
		LocalDateTime ld2 = LocalDateTime.of(2016, 11, 21, 10, 10, 10);
		System.out.println(ld2);
		
		LocalDateTime ldt3 = ld2.plusYears(20);
		System.out.println(ldt3);
		
		LocalDateTime ldt4 = ld2.minusMonths(2);
		System.out.println(ldt4);
		
		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonthValue());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());

	}

	@Test
	public void testLocalDate(){
		LocalDate today = LocalDate.now();
		System.out.println("今天的日期："+today);
		LocalDate oneday = LocalDate.of(2016,10,22);

		//取2016年10月的第1天
		LocalDate firstDay = oneday.with(TemporalAdjusters.firstDayOfMonth());
		System.out.println(firstDay);

		//取2016年10月的第1天，另外一种写法
		LocalDate firstDay2 = oneday.withDayOfMonth(1);
		System.out.println(firstDay2);

		//取2016年10月的最后1天，不用考虑大月，小月，平年，闰年
		LocalDate lastDay = oneday.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println(lastDay);

		//当前日期＋1天
		LocalDate tomorrow = oneday.plusDays(1);
		System.out.println(tomorrow);

		//判断是否为闰年
		boolean isLeapYear = tomorrow.isLeapYear();
		System.out.println(isLeapYear);

		LocalDate birthday = LocalDate.of(1990, 10, 12);
		MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
		MonthDay today2 = MonthDay.from(LocalDate.of(2016, 10, 12));
		System.out.println(today2.equals(birthdayMd));
	}

	/**
	 * 日期主要是使用LocalTime，该类不包含日期，只有时间信息
	 */
	@Test
	public void testLocalTime(){
		//获取当前的时间
		LocalTime nowTime = LocalTime.now(); //结果14:29:40.558
		System.out.println(nowTime);
		//如果不想显示毫秒
		LocalTime nowTime2 = LocalTime.now().withNano(0); //14:43:14
		System.out.println(nowTime2);
		//指定时间
		LocalTime time = LocalTime.of(14, 10, 21); //14:10:21
		LocalTime time2 = LocalTime.parse("12:00:01"); // 12:00:01

		//当前时间增加2小时
		LocalTime nowTimePlus2Hour = nowTime.plusHours(2); //16:47:23.144
		//或者
		LocalTime nowTimePlus2Hour2 = nowTime.plus(2, ChronoUnit.HOURS);

	}
}
