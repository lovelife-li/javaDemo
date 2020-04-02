package com.study.javanewspecial.java5.day2;

import java.util.Date;

public class ClassLoaderTest {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
	
		System.out.println("gg");
		String s = ClassLoaderTest.class.getClassLoader().getClass().getName();
		System.out.println(s);
		
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		System.out.println(loader);
		//System.out.println(new ClassLoaderAttachment().toString());
		
//		Class clazz = new MyClassLoader("itcastlib").loadClass("cn.itcast.ClassLoaderAttachment");
//		Date d1 = (Date) clazz.newInstance();
//		System.out.println(d1);
		
		//System.out.println(new ClassLoaderAttachment().toString()+"ϵͳ");
	}

}
