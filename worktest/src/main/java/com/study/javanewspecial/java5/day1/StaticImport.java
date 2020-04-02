package com.study.javanewspecial.java5.day1;
import static java.lang.Math.abs;
public class StaticImport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("gg");
//		System.out.println(abs(2-6));
//		System.out.println(add(2,3));
		System.out.println(add(2,3,5));
		Integer num1 = 137;
		Integer num2 = 137;
		System.out.println(num1==num2);
		Integer num3 = 127;
		Integer num4 = 127;
		System.out.println(num3==num4);
	
		Integer i3 = Integer.valueOf(num1);
		Integer i4 = Integer.valueOf(num2);
		System.out.println(i3==i4);
		
		int weekDay = 6;
		
		WeekDay day = WeekDay.MON;
		System.out.println(day);
		System.out.println(day.name());
		System.out.println(day.ordinal());
		System.out.println(day.getClass());
		System.out.println(WeekDay.valueOf("SUN"));
		System.out.println(WeekDay.values().length);
		
		TrafficLamp lamp = TrafficLamp.GREEN;
		System.out.println(lamp.time);
		
		
	}

	public static int add(int a,int ... args){
		int sum=a;
//		for(int i=0;i<args.length;i++){
//			sum+=args[i];
//		}
		for(int arg : args){
			sum+=arg;
		}
		return sum;
	}
	public enum WeekDay{
		SUN(1),MON,TUE,WEN,THI,FRI,SAT;
		
		private WeekDay(){
			System.out.println("hello");
		}
		private WeekDay(int day){
			System.out.println("world");
		}
	}
	
	public enum TrafficLamp{
		RED(30) {
			@Override
			public TrafficLamp nextLamp() {
				// TODO Auto-generated method stub
				return GREEN;
			}
		},YELLOW (5){
			@Override
			public TrafficLamp nextLamp() {
				// TODO Auto-generated method stub
				return RED;
			}
		},GREEN(45) {
			@Override
			public TrafficLamp nextLamp() {
				// TODO Auto-generated method stub
				return YELLOW;
			}
		};
		
		public abstract TrafficLamp nextLamp();
		public int time;
		private TrafficLamp(int time){
			this.time = time;
		}
	}
}

