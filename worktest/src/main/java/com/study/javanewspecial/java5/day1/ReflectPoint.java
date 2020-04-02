package com.study.javanewspecial.java5.day1;

import java.util.Date;

public class ReflectPoint {

	private Date birthDay = new Date();
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	private int x;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int y;
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String str1 = "back";
	public String str2 ="hello,bb,xxaa;";
	public String str3 = "world";
	public ReflectPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReflectPoint other = (ReflectPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	

}
