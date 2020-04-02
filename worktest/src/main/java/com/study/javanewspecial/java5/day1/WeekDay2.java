package com.study.javanewspecial.java5.day1;

public abstract class WeekDay2 {

	private WeekDay2(){
		
	}
	private final static WeekDay2 SUN = new WeekDay2(){
		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return MON;
		}
	};
	private final static WeekDay2 MON = new WeekDay2(){
		@Override
		public WeekDay2 nextDay() {
			// TODO Auto-generated method stub
			return SUN;
		}
	};
	
	public abstract WeekDay2 nextDay();
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this==SUN?"SUN":"MON";
	}
}
