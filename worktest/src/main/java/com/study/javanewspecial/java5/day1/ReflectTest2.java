package com.study.javanewspecial.java5.day1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

public class ReflectTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		InputStream stream = ReflectTest2.class.getResourceAsStream("/config.properties");
		
		Properties properties = new Properties();
		properties.load(stream);
		stream.close();
		String className = (String) properties.get("className");
		Collection collection = (Collection) Class.forName(className).newInstance();
		// TODO Auto-generated method stub
		//Collection collection = new HashSet();
		ReflectPoint point1 = new ReflectPoint(3,3);
		ReflectPoint point2 = new ReflectPoint(5,5);
		ReflectPoint point3 = new ReflectPoint(3,3);
		
		collection.add(point1);
		collection.add(point2);
		collection.add(point3);
		collection.add(point1);
		
		point1.y =7;
		collection.remove(point1);
		System.out.println(collection.size());
		
		
		
	}

}
