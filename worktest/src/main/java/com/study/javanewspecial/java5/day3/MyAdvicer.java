package com.study.javanewspecial.java5.day3;

import java.lang.reflect.Method;

public class MyAdvicer implements Advice{

	long beginTime = 0;
	@Override
	public void afterMethod(Method method) {
		// TODO Auto-generated method stub
		System.out.println("方法调用后：");
		long endTime = System.currentTimeMillis();
		System.out.println(method.getName() + " 运行时间:" + (endTime - beginTime));
	}

	@Override
	public void beforeMethod(Method method) {
		System.out.println("方法调用前：");
		// TODO Auto-generated method stub
		beginTime = System.currentTimeMillis();
	}

}
