package com.study.java8.demo02.java8;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatThreadLocal {
	
	private static final ThreadLocal<SimpleDateFormat> df = new ThreadLocal<SimpleDateFormat>(){
		
		protected SimpleDateFormat initialValue(){
			return new SimpleDateFormat();
		}
		
	};
	
	public static final Date convert(String source) throws ParseException{
		return df.get().parse(source);
	}

	public static void main(String[] args) throws ParseException {
		df.get().applyPattern("yyyy-MM-dd");
		Date date = df.get().parse("2011-11-11 12:12:12");
		df.get().applyPattern("yyyy-MM-dd HH:mm:ss");
		Date date2 = df.get().parse("2011-11-11 12:12:12");
		System.out.println(date2);


	}

}
