package com.study.javanewspecial.java5.day3;

import java.lang.reflect.Method;

public interface Advice {

	//一般有4 个方法
	void beforeMethod(Method m);
	void afterMethod(Method m);
}
