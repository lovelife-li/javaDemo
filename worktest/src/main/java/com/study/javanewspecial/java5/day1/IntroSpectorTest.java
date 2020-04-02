package com.study.javanewspecial.java5.day1;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class IntroSpectorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ReflectPoint point = new ReflectPoint(3, 5);
		
		String propertyName = "x";
		
		getProperty(point, propertyName);
		BeanUtils.setProperty(point, "x", 8);
		System.out.println(BeanUtils.getProperty(point, "x"));
		
		BeanUtils.setProperty(point, "birthDay.time", "111");
		System.out.println(BeanUtils.getProperty(point, "birthDay.time"));
		
		//java7新特性
//		Map map = {name:"xxx", age:15};
//		BeanUtils.setProperty(map, "name", "lhm");
		
		PropertyUtils.setProperty(point, "x", 10);
		System.out.println(point.getX());
		
	}

	private static Object getProperty(Object point, String propertyName)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
//		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, point.getClass());
//		Method m = descriptor.getReadMethod();
//		Object returnVal = m.invoke(point);
		
		Object returnVal = null;
		BeanInfo beanInfo = Introspector.getBeanInfo(point.getClass());
		PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor p :descriptors){
			if(p.getName().equals(propertyName)){
				Method m = p.getReadMethod();
				returnVal = m.invoke(point);
				break;
			}
		}
		
		return returnVal;
		
	}

	private static void setPropertity(Object point, String propertyName,Object value)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, point.getClass());
		Method m2 = descriptor.getWriteMethod();
		m2.invoke(point,value);
	}

}
