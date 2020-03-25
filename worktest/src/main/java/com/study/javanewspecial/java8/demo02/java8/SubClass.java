package com.study.javanewspecial.java8.demo02.java8;

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface {


	@Override
	public String getName() {
		return MyFun.super.getName();
	}



}
