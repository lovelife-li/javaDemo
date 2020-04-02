package com.study.javanewspecial.java5.day3.aopFramework;

import java.io.InputStream;
import java.util.Collection;

public class AopFramworkTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStream is = AopFramworkTest.class.getResourceAsStream("/config.properties");
		Object bean = new BeanFactory(is).getBean("className");
		System.out.println(bean.getClass().getName());
		Collection collection = (Collection) bean;
		collection.add("13");
	}

}
